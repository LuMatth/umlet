package com.baselet.element.pap.facet;

import java.util.List;

import com.baselet.control.basics.geom.DimensionDouble;
import com.baselet.diagram.draw.DrawHandler;
import com.baselet.element.facet.PropertiesParserState;
import com.baselet.element.nash.facet.Containable;

public class BranchSegment implements Containable {

	private final String condition;
	private final PapContainer commandsTrue;
	private final PapContainer commandsFalse;

	private final double drawHeight = 45.0;
	private double height;

	public BranchSegment(String condition, List<String> codeTrue, List<String> codeFalse) {
		this.condition = condition;
		commandsTrue = new PapContainer(codeTrue);
		commandsFalse = new PapContainer(codeFalse);
	}

	@Override
	public DimensionDouble calculateDimension(DrawHandler drawer) {
		// TODO

		return null;
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
