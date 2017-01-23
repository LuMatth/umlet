package com.baselet.element.pap.facet;

import com.baselet.control.basics.geom.DimensionDouble;
import com.baselet.diagram.draw.DrawHandler;
import com.baselet.element.facet.PropertiesParserState;
import com.baselet.element.nash.facet.Containable;

public class CommandSegment implements Containable {

	private final String command;
	private final double height = 45;

	public CommandSegment(String command) {
		this.command = command;
	}

	@Override
	public DimensionDouble calculateDimension(DrawHandler drawer) {
		return new DimensionDouble(drawer.textWidth(command) * 1.5, height);
	}

	@Override
	public void draw(DrawHandler drawer, PropertiesParserState state, double xOffset, double yOffset, double width) {
		// TODO
	}

	@Override
	public double getHeight() {
		return height;
	}

}
