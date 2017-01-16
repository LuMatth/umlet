package com.baselet.element.nash.facet;

import java.util.List;

import com.baselet.control.basics.geom.DimensionDouble;
import com.baselet.control.enums.AlignHorizontal;
import com.baselet.diagram.draw.DrawHandler;
import com.baselet.element.facet.PropertiesParserState;

public class NashDiagram {

	NashContainer algorithm;
	DimensionDouble dimension;

	String name;
	boolean hasName;

	public NashDiagram(List<String> code) {
		int counter = 0;
		for (counter = 0; counter < code.size(); counter++) {
			if (!code.get(counter).isEmpty()) {
				break;
			}
		}
		if (code.size() >= 1 && code.get(counter).startsWith("name=")) {
			name = code.get(counter).substring(5).trim();
			hasName = true;
			algorithm = new NashContainer(code.subList(counter + 1, code.size()));
		}
		else {
			algorithm = new NashContainer(code);
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
		double yOffset = 0;

		if (hasName) {
			drawer.print(name, 0, 45.0 / 2.0 + drawer.getDistanceBorderToText() + drawer.textHeight(name), AlignHorizontal.LEFT);
			yOffset += 45.0;
		}
		algorithm.draw(drawer, state, 0, yOffset, dimension.getWidth());
	}
}
