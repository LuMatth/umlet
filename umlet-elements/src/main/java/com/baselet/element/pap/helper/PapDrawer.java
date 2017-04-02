package com.baselet.element.pap.helper;

import com.baselet.diagram.draw.DrawHandler;
import com.baselet.element.facet.PropertiesParserState;

public class PapDrawer {

	private static final double ARROW_LENGTH = 6.0;
	private static final double ARROW_WIDTH = 6.0;

	public static void drawArrowDown(DrawHandler drawer, PropertiesParserState state, double x, double y, double length) {
		drawer.drawLine(x, y, x, y + length - ARROW_LENGTH);

		// Draw Arrow Head
		drawer.drawLine(x - ARROW_WIDTH / 2.0, y + length - ARROW_LENGTH, x + ARROW_WIDTH / 2.0, y + length - ARROW_LENGTH);
		drawer.drawLine(x - ARROW_WIDTH / 2.0, y + length - ARROW_LENGTH, x, y + length);
		drawer.drawLine(x + ARROW_WIDTH / 2.0, y + length - ARROW_LENGTH, x, y + length);
	}

}
