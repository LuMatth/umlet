package com.baselet.element.nash.facet;

import com.baselet.control.basics.geom.DimensionDouble;
import com.baselet.diagram.draw.DrawHandler;

public class BranchBlock implements Containable {
	private String condition;
	private NashContainer commandsTrue;
	private NashContainer commandsFalse;

	@Override
	public DimensionDouble calculateDimension(DrawHandler drawer) {
		DimensionDouble left, right;
		double width, height;

		width = drawer.textWidth(condition) * 4;
		height = drawer.textHeight(condition) * 4;

		left = commandsTrue.calculateDimension(drawer);
		right = commandsFalse.calculateDimension(drawer);

		width = Math.max(width, Math.max(left.getWidth() * 2, right.getWidth() * 2));

		return new DimensionDouble(width, height);
	}

}
