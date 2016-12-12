package com.baselet.element.nash.facet;

import com.baselet.control.basics.geom.DimensionDouble;
import com.baselet.diagram.draw.DrawHandler;
import com.baselet.element.facet.PropertiesParserState;

public interface Containable {

	public DimensionDouble calculateDimension(DrawHandler drawer);

	public void draw(DrawHandler drawer, PropertiesParserState state, double xOffset, double yOffset, double width);

	public double getHeight();

}
