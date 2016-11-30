package com.baselet.element.nash.facet;

import com.baselet.control.basics.geom.DimensionDouble;
import com.baselet.diagram.draw.DrawHandler;

public interface Containable {

	public DimensionDouble calculateDimension(DrawHandler drawer);

}
