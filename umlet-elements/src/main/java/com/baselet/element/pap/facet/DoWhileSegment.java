package com.baselet.element.pap.facet;

import java.util.List;

import com.baselet.control.basics.geom.DimensionDouble;
import com.baselet.control.enums.AlignHorizontal;
import com.baselet.diagram.draw.DrawHandler;
import com.baselet.element.facet.PropertiesParserState;
import com.baselet.element.nash.facet.Containable;
import com.baselet.element.pap.helper.PapDrawer;

public class DoWhileSegment implements Containable {

	private final String condition;
	private final PapContainer commands;

	private final double condition_height = 40;
	private final double condition_min_width = 40;
	private final double condition_scale_factor = 25.0;
	private final double arrowLength = 40;
	private final double loopWidth = 10;

	private double height = 0;
	private double width = 0;

	public DoWhileSegment(String condition, List<String> code) {
		this.condition = condition;
		commands = new PapContainer(code);
	}

	@Override
	public DimensionDouble calculateDimension(DrawHandler drawer) {
		DimensionDouble dimension = commands.calculateDimension(drawer);

		width = dimension.getWidth() + Math.max(condition_min_width, drawer.textWidth(condition) + condition_scale_factor) + loopWidth;
		width *= 2;
		height = dimension.getHeight() + arrowLength + condition_height;

		return new DimensionDouble(width, height);
	}

	@Override
	public void draw(DrawHandler drawer, PropertiesParserState state, double xOffset, double yOffset, double w) {
		commands.draw(drawer, state, xOffset, yOffset, width);

		PapDrawer.drawArrowDown(drawer, xOffset, yOffset + height - condition_height - arrowLength, arrowLength);
		drawer.print(condition, xOffset, yOffset + height - condition_height / 2.0 + drawer.getDistanceBorderToText(), AlignHorizontal.CENTER);

		// draw Diamond
		drawer.drawLine(xOffset - Math.max(condition_min_width, drawer.textWidth(condition) + condition_scale_factor) / 2.0, yOffset + height - condition_height / 2.0, xOffset, yOffset + height - condition_height);
		drawer.drawLine(xOffset, yOffset + height - condition_height, xOffset + Math.max(condition_min_width, drawer.textWidth(condition) + condition_scale_factor) / 2.0, yOffset + height - condition_height / 2.0);
		drawer.drawLine(xOffset - Math.max(condition_min_width, drawer.textWidth(condition) + condition_scale_factor) / 2.0, yOffset + height - condition_height / 2.0, xOffset, yOffset + height);
		drawer.drawLine(xOffset, yOffset + height, xOffset + Math.max(condition_min_width, drawer.textWidth(condition) + condition_scale_factor) / 2.0, yOffset + height - condition_height / 2.0);

		// draw arrow to the start of the loop
		drawer.drawLine(xOffset + Math.max(condition_min_width, drawer.textWidth(condition) + condition_scale_factor) / 2.0, yOffset + height - condition_height / 2.0, xOffset + width, yOffset + height - condition_height / 2.0);
		drawer.drawLine(xOffset + width, yOffset + height - condition_height / 2.0, xOffset + width, yOffset + arrowLength / 2.0);
		PapDrawer.drawArrowLeft(drawer, xOffset + width, yOffset + arrowLength / 2.0, width);

		// print true and false
		drawer.print("true", xOffset + Math.max(condition_min_width, drawer.textWidth(condition) + condition_scale_factor) + drawer.getDistanceBorderToText() * 2, yOffset + height - condition_height / 2.0 - drawer.getDistanceBorderToText(), AlignHorizontal.CENTER);
		drawer.print("false", xOffset + drawer.textWidth("false"), yOffset + height + arrowLength / 2.0, AlignHorizontal.CENTER);
	}

	@Override
	public double getHeight() {
		return height;
	}

}
