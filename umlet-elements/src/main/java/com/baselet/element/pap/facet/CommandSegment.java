package com.baselet.element.pap.facet;

import com.baselet.control.basics.geom.DimensionDouble;
import com.baselet.control.enums.AlignHorizontal;
import com.baselet.diagram.draw.DrawHandler;
import com.baselet.element.facet.PropertiesParserState;
import com.baselet.element.nash.facet.Containable;
import com.baselet.element.pap.helper.PapDrawer;

public class CommandSegment implements Containable {

	private final String command;
	private final double height = 35;
	private final double arrowLength = 40;

	public CommandSegment(String command) {
		this.command = command;
	}

	@Override
	public DimensionDouble calculateDimension(DrawHandler drawer) {
		return new DimensionDouble(drawer.textWidth(command) * 1.5, height + arrowLength);
	}

	@Override
	public void draw(DrawHandler drawer, PropertiesParserState state, double xOffset, double yOffset, double width) {
		PapDrawer.drawArrowDown(drawer, xOffset, yOffset, arrowLength);
		drawer.drawRectangle(xOffset - drawer.textWidth(command) * 1.5 / 2.0, yOffset + arrowLength, drawer.textWidth(command) * 1.5, height);
		drawer.print(command, xOffset, yOffset + arrowLength + height / 2.0 + drawer.getDistanceBorderToText(), AlignHorizontal.CENTER);
	}

	@Override
	public double getHeight() {
		return height + arrowLength;
	}

}
