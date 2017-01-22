package com.baselet.element.pap.facet;

import java.util.List;

import com.baselet.control.basics.geom.DimensionDouble;
import com.baselet.diagram.draw.DrawHandler;
import com.baselet.element.facet.PropertiesParserState;
import com.baselet.element.nash.facet.Containable;

public class DoWhileSegment implements Containable {

	private final String condition;
	private final PapContainer commands;

	private final double drawHeight = 45.0;
	private final double drawWidth = 45.0;

	private double height = 0;

	public DoWhileSegment(String condition, List<String> code) {
		this.condition = condition;
		commands = new PapContainer(code);
	}

	@Override
	public DimensionDouble calculateDimension(DrawHandler drawer) {
		DimensionDouble dimension = commands.calculateDimension(drawer);

		double width = dimension.getWidth() + drawWidth;
		height = dimension.getHeight() + drawHeight;

		return new DimensionDouble(width, height);
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
