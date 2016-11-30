package com.baselet.element.nash.facet;

import com.baselet.control.basics.geom.DimensionDouble;
import com.baselet.diagram.draw.DrawHandler;

public class CommandBlock implements Containable {

	private String command;

	@Override
	public DimensionDouble calculateDimension(DrawHandler drawer) {
		return new DimensionDouble(drawer.textWidth(command) * 1.5, drawer.textHeight(command) * 1.5);
	}

}
