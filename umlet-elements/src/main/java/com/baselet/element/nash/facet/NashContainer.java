package com.baselet.element.nash.facet;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.baselet.control.basics.geom.DimensionDouble;
import com.baselet.diagram.draw.DrawHandler;
import com.baselet.element.facet.PropertiesParserState;

public class NashContainer {
	List<Containable> blocks;

	public NashContainer(List<String> code) {
		blocks = new LinkedList<Containable>();
		generateFromCode(code);
	}

	public DimensionDouble calculateDimension(DrawHandler drawer) {
		double width = 0, height = 0;

		for (Containable c : blocks) {
			DimensionDouble d = c.calculateDimension(drawer);

			width = Math.max(width, d.getWidth());
			height += d.getHeight();

		}

		return new DimensionDouble(width, height);
	}

	public void draw(DrawHandler drawer, PropertiesParserState state, double xOffset, double yOffset, double width) {
		double n_xOffset = xOffset;
		double n_yOffset = yOffset;

		for (Containable c : blocks) {
			c.draw(drawer, state, n_xOffset, n_yOffset, width);
			n_yOffset += c.getHeight();
		}

	}

	private void generateFromCode(List<String> code) {

		for (int i = 0; i < code.size(); ++i) {
			String line = code.get(i).trim();
			if (line.isEmpty()) {
				continue;
			}
			if (line.startsWith("while")) {
				// Ignore While at the moment!
			}
			if (line.startsWith("if")) {
				List<String> commandsTrue = new ArrayList<String>();
				List<String> commandsFalse = new ArrayList<String>();

				int b, x; // used to switch between true and false
				int ignoreEnds = 0; // used to detect nested if statements

				for (b = i + 1; b < code.size(); b++) {
					if (code.get(b).trim().startsWith("if")) {
						ignoreEnds++;
					}
					if (code.get(b).trim().startsWith("else") && ignoreEnds == 0) {
						break;
					}
					else if (code.get(b).trim().startsWith("else")) {
						ignoreEnds--;
					}
					commandsTrue.add(code.get(b).trim());
				}
				ignoreEnds = 0;
				for (x = b + 1; x < code.size(); x++) {
					if (code.get(x).trim().startsWith("if")) {
						ignoreEnds++;
					}
					if (code.get(x).trim().startsWith("endif") && ignoreEnds == 0) {
						break;
					}
					else if (code.get(x).trim().startsWith("endif")) {
						ignoreEnds--;
					}
					commandsFalse.add(code.get(x).trim());
				}
				i = x;
				blocks.add(new BranchBlock(line.substring(2), commandsTrue, commandsFalse));
			}
			else {
				blocks.add(new CommandBlock(line));
			}
		}

	}
}
