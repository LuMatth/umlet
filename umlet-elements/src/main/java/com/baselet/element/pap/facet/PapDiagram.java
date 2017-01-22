package com.baselet.element.pap.facet;

import java.util.List;

import com.baselet.control.basics.geom.DimensionDouble;
import com.baselet.diagram.draw.DrawHandler;
import com.baselet.element.facet.PropertiesParserState;

public class PapDiagram {
	PapContainer algorithm;
	DimensionDouble dimension;

	String name;
	boolean hasName;

	public PapDiagram(List<String> code) {
		int counter = 0;
		for (counter = 0; counter < code.size(); counter++) {
			if (!code.get(counter).isEmpty()) {
				break;
			}
		}
		if (code.size() >= 1 && code.get(counter).startsWith("name=")) {
			name = code.get(counter).substring(5).trim();
			hasName = true;
			algorithm = new PapContainer(code.subList(counter + 1, code.size()));
		}
		else {
			algorithm = new PapContainer(code);
			hasName = false;
		}
	}

	public DimensionDouble getDimension(DrawHandler drawer) {
		dimension = algorithm.calculateDimension(drawer);
		if (hasName) {
			dimension = new DimensionDouble(dimension.getWidth(), dimension.getHeight() + 45.0);
		}
		return dimension;
	}

	public void draw(DrawHandler drawer, PropertiesParserState state) {
		throw new UnsupportedOperationException();
	}
}
