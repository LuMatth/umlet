package com.baselet.element.nash.facet;

import java.util.List;

import com.baselet.control.basics.geom.DimensionDouble;
import com.baselet.diagram.draw.DrawHandler;
import com.baselet.element.facet.PropertiesParserState;

public class BranchBlock implements Containable {
	private final String condition;
	private final NashContainer commandsTrue;
	private final NashContainer commandsFalse;

	public BranchBlock(String condition, List<String> codeTrue, List<String> codeFalse) {
		this.condition = condition;
		commandsTrue = new NashContainer(codeTrue);
		commandsFalse = new NashContainer(codeFalse);
	}

	@Override
	public DimensionDouble calculateDimension(DrawHandler drawer) {
		DimensionDouble left, right;
		double width, height;

		width = drawer.textWidth(condition) * 4;
		height = drawer.textHeight(condition) * 4;

		left = commandsTrue.calculateDimension(drawer);
		right = commandsFalse.calculateDimension(drawer);

		width = Math.max(width, Math.max(left.getWidth() * 2, right.getWidth() * 2));
		height = Math.max(left.getHeight(), right.getHeight()) + height;

		return new DimensionDouble(width, height);
	}

	@Override
	public void draw(DrawHandler drawer, PropertiesParserState state, double xOffset, double yOffset, double width) {
		// TODO Auto-generated method stub

	}

	@Override
	public double getHeight() {
		// TODO Auto-generated method stub
		return 0;
	}

}
