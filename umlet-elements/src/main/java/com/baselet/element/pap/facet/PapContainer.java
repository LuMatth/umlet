package com.baselet.element.pap.facet;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.baselet.control.basics.geom.DimensionDouble;
import com.baselet.diagram.draw.DrawHandler;
import com.baselet.element.facet.PropertiesParserState;
import com.baselet.element.nash.facet.Containable;

public class PapContainer {
	List<Containable> blocks;

	public PapContainer(List<String> code) {
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
			if (line.startsWith("while ")) {
				List<String> commands = new ArrayList<String>();
				int ignoreEnds = 0;
				int c;
				for (c = i + 1; c < code.size(); c++) {
					String parseLine = code.get(c).trim();
					if (parseLine.startsWith("while ")) {
						ignoreEnds++;
					}
					if (parseLine.equals("endwhile") && ignoreEnds == 0) {
						break;
					}
					if (parseLine.equals("endwhile")) {
						ignoreEnds--;
					}
					commands.add(parseLine);
				}
				i = c;
				blocks.add(new WhileSegment(line.substring(6), commands));
				continue;
			}
			if (line.equals("do")) {
				List<String> commands = new ArrayList<String>();
				int ignoreEnds = 0;
				int c;
				for (c = i + 1; c < code.size(); c++) {
					String parseLine = code.get(c).trim();
					if (parseLine.equals("do")) {
						ignoreEnds++;
					}
					if (parseLine.startsWith("dowhile ") && ignoreEnds == 0) {
						line = parseLine.substring(8);
						break;
					}
					if (parseLine.startsWith("dowhile ")) {
						ignoreEnds--;
					}
					commands.add(parseLine);
				}
				i = c;
				blocks.add(new DoWhileSegment(line, commands));
				continue;
			}
			if (line.startsWith("if")) {
				List<String> commandsTrue = new ArrayList<String>();
				List<String> commandsFalse = new ArrayList<String>();

				int b, x; // used to switch between true and false
				int ignoreEnds = 0; // used to detect nested if statements
				boolean skipSecondLoop = false;
				String parseLine;
				for (b = i + 1; b < code.size(); b++) {
					parseLine = code.get(b).trim();
					if (parseLine.startsWith("if")) {
						ignoreEnds++;
					}
					if (parseLine.equals("else") && ignoreEnds == 0) {
						break;
					}
					else if (parseLine.equals("endif") && ignoreEnds == 0) {
						skipSecondLoop = true;
						b--;
						break;
					}
					else if (parseLine.equals("endif")) {
						ignoreEnds--;
					}

					commandsTrue.add(parseLine);
				}
				ignoreEnds = 0;
				for (x = b + 1; !skipSecondLoop && x < code.size(); x++) {
					parseLine = code.get(x).trim();
					if (parseLine.startsWith("if")) {
						ignoreEnds++;
					}
					if (parseLine.equals("endif") && ignoreEnds == 0) {
						break;
					}
					else if (parseLine.equals("endif")) {
						ignoreEnds--;
					}
					commandsFalse.add(parseLine);
				}
				i = x;
				blocks.add(new BranchSegment(line.substring(2).trim(), commandsTrue, commandsFalse));
				continue;
			}
			else {
				blocks.add(new CommandSegment(line));
				continue;
			}
		}

	}

}
