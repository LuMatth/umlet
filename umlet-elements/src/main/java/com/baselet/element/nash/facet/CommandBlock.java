package com.baselet.element.nash.facet;

import com.baselet.control.basics.geom.DimensionDouble;
import com.baselet.control.enums.AlignHorizontal;
import com.baselet.diagram.draw.DrawHandler;
import com.baselet.element.facet.PropertiesParserState;

public class CommandBlock implements Containable {

	private final String command;
	private final double height = 45;

	public CommandBlock(String command) {
		this.command = command;
	}

	@Override
	public DimensionDouble calculateDimension(DrawHandler drawer) {
		return new DimensionDouble(drawer.textWidth(command) * 1.5, height);
	}

	@Override
	public void draw(DrawHandler drawer, PropertiesParserState state, double xOffset, double yOffset, double width) {
		drawer.drawLine(xOffset, yOffset, width, yOffset);
		drawer.drawLine(xOffset, yOffset + height, width, yOffset + height);
		drawer.drawLine(xOffset, yOffset, xOffset, yOffset + height);
		drawer.drawLine(width, yOffset, width, yOffset + height);
		drawer.print(command, xOffset + (width - xOffset) / 2, yOffset + drawer.getDistanceBorderToText() + height / 2.0, AlignHorizontal.CENTER);

	}

	@Override
	public double getHeight() {
		return height;
	}

}
