package com.baselet.element.nash.facet;

import java.util.List;

import com.baselet.control.basics.geom.DimensionDouble;
import com.baselet.diagram.draw.DrawHandler;

public class NashDiagram {

	NashContainer algorithm;

	public NashDiagram(List<String> code) {
		algorithm = new NashContainer(code);
	}

	public DimensionDouble getDimension(DrawHandler drawer) {
		return algorithm.calculateDimension(drawer);
	}
}
