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

		width = drawer.textWidth(condition) * 1.5;
		height = drawer.textHeight(condition) * 1.5;

		left = commandsTrue.calculateDimension(drawer);
		right = commandsFalse.calculateDimension(drawer);

		width = Math.max(width, Math.max(left.getWidth(), right.getWidth()));
		height = Math.max(height, Math.max(left.getHeight(), right.getHeight()));

		return new DimensionDouble(width, height);
	}

}
