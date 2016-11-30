package com.baselet.element.nash.facet;

import java.util.List;

import com.baselet.control.basics.geom.DimensionDouble;
import com.baselet.diagram.draw.DrawHandler;
import com.baselet.element.facet.PropertiesParserState;

public class NashDiagram {

	NashContainer algorithm;
	DimensionDouble dimension;

	public NashDiagram(List<String> code) {
		algorithm = new NashContainer(code);
	}

	public DimensionDouble getDimension(DrawHandler drawer) {
		dimension = algorithm.calculateDimension(drawer);
		return dimension;
	}

	public void draw(DrawHandler drawer, PropertiesParserState state) {
		algorithm.draw(drawer, state, 0, 0, dimension.getWidth());
	}
}
