package com.baselet.element.nash.facet;

import java.util.LinkedList;
import java.util.List;

import com.baselet.control.basics.geom.DimensionDouble;
import com.baselet.diagram.draw.DrawHandler;

public class NashContainer {
	List<Containable> blocks;

	public NashContainer(List<String> code) {
		blocks = new LinkedList<Containable>();
	}

	public DimensionDouble calculateDimension(DrawHandler drawer) {
		double width = 0, height = 0;

		for (Containable c : blocks) {
			DimensionDouble d = c.calculateDimension(drawer);

			width = Math.max(width, d.getWidth());
			height = Math.max(height, d.getHeight());

		}

		return new DimensionDouble(width, height);
	}
}
