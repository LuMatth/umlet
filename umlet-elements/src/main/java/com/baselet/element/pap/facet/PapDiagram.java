package com.baselet.element.pap.facet;

import java.util.List;

import com.baselet.control.basics.geom.DimensionDouble;
import com.baselet.control.enums.AlignHorizontal;
import com.baselet.diagram.draw.DrawHandler;
import com.baselet.element.facet.PropertiesParserState;
import com.baselet.element.pap.helper.PapDrawer;

public class PapDiagram {
	PapContainer algorithm;
	DimensionDouble dimension;

	private final double ellipseDim = 30.0;
	private final double arrowLength = 40;

	public PapDiagram(List<String> code) {
		algorithm = new PapContainer(code);
	}

	public DimensionDouble getDimension(DrawHandler drawer) {
		dimension = algorithm.calculateDimension(drawer);
		double width = dimension.getWidth() + ellipseDim * 2;
		double height = dimension.getHeight() + ellipseDim * 2 + arrowLength;
		dimension = new DimensionDouble(width, height);

		return dimension;
	}

	public void draw(DrawHandler drawer, PropertiesParserState state) {
		drawer.setFontSize(14);
		drawer.drawEllipse(dimension.getWidth() / 2.0 - ellipseDim, 0, ellipseDim * 2, ellipseDim);
		drawer.print("Start", dimension.getWidth() / 2.0, ellipseDim / 2.0 + drawer.getDistanceBorderToText(), AlignHorizontal.CENTER);
		algorithm.draw(drawer, state, dimension.getWidth() / 2.0, ellipseDim, dimension.getWidth());
		PapDrawer.drawArrowDown(drawer, dimension.getWidth() / 2.0, dimension.getHeight() - arrowLength - ellipseDim, arrowLength);
		drawer.drawEllipse(dimension.getWidth() / 2.0 - ellipseDim, dimension.getHeight() - ellipseDim, ellipseDim * 2.0, ellipseDim);
		drawer.print("End", dimension.getWidth() / 2.0, dimension.getHeight() - ellipseDim / 2.0 + drawer.getDistanceBorderToText(), AlignHorizontal.CENTER);
	}
}
