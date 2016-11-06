package com.baselet.element.nash.facet;

import java.util.ArrayList;
import java.util.List;

import com.baselet.control.basics.geom.Dimension;
import com.baselet.control.enums.AlignHorizontal;
import com.baselet.control.enums.LineType;
import com.baselet.diagram.draw.DrawHandler;
import com.baselet.diagram.draw.DrawHandler.Layer;
import com.baselet.element.facet.Facet;
import com.baselet.element.facet.PropertiesParserState;
import com.baselet.gui.AutocompletionText;

public class DoWhileFacet extends Facet {

	public static final DoWhileFacet INSTANCE = new DoWhileFacet();

	private static final String KEY_PREFIX = "|_";

	private static final double X_SPACE = 15;

	private final double X_WIDTH = 40.0;
	private final double Y_HEIGHT = 40.0;

	@Override
	public boolean checkStart(String line, PropertiesParserState state) {
		return line.equals(KEY_PREFIX);
	}

	@Override
	public void handleLine(String line, PropertiesParserState state) {
		DrawHandler drawer = state.getDrawer();
		LineType ltBefore = drawer.getLineType();

		drawer.setLineType(LineType.SOLID);
		drawer.setLayer(Layer.Foreground);

		Dimension dimension = state.getGridElementSize();

		if (dimension.height < Y_HEIGHT * 2) {
			String message = "Error: To Short for Do-While-Block!";
			drawer.print(message, 0 + X_SPACE, drawer.getDistanceBorderToText() + drawer.textHeight(message) + drawer.getDistanceBetweenTextLines(), AlignHorizontal.LEFT);
			return;
		}

		// draw Loop Frame
		drawer.drawLine(0, 0, X_WIDTH, 0);
		drawer.drawLine(0, 0, 0, dimension.height);
		drawer.drawLine(X_WIDTH, 0, X_WIDTH, dimension.height - Y_HEIGHT);
		drawer.drawLine(0, dimension.height, dimension.width, dimension.height);
		drawer.drawLine(X_WIDTH, dimension.height - Y_HEIGHT, dimension.width, dimension.height - Y_HEIGHT);
		drawer.drawLine(dimension.width, dimension.height - Y_HEIGHT, dimension.width, dimension.height);

		// print Do and While
		drawer.print("do", 0 + X_SPACE, drawer.getDistanceBorderToText() + drawer.textHeight("do") + drawer.getDistanceBetweenTextLines() + dimension.height / 3.0, AlignHorizontal.LEFT);
		double textHeight = dimension.height - (drawer.getDistanceBorderToText() + drawer.textHeight("while") + drawer.getDistanceBetweenTextLines());
		drawer.print("while", 0 + X_SPACE, textHeight, AlignHorizontal.LEFT);
		state.increaseTextPrintPosition(textHeight / 2.0 - drawer.getDistanceBorderToText() - drawer.getDistanceBetweenTextLines());

		drawer.setLayer(Layer.Background);
		drawer.setLineType(ltBefore);

	}

	@Override
	public List<AutocompletionText> getAutocompletionStrings() {
		List<AutocompletionText> returnList = new ArrayList<AutocompletionText>();

		returnList.add(new AutocompletionText(DoWhileFacet.KEY_PREFIX, "Draw NASH Do-While Loop"));

		return returnList;
	}

	@Override
	public boolean handleOnFirstRun() {
		return true;
	}

}
