package com.baselet.element.nash.facet;

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
			if (line.startsWith("while")) {
				// Ignore While at the moment!
			}
			if (line.startsWith("if")) {
				// Ignore If at the moment
			}
			else {
				blocks.add(new CommandBlock(line));
			}
		}

	}
}
