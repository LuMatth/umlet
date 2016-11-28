package com.baselet.element.nash.facet;

import java.util.ArrayList;
import java.util.List;

import com.baselet.control.basics.XValues;
import com.baselet.control.enums.AlignHorizontal;
import com.baselet.control.enums.LineType;
import com.baselet.diagram.draw.DrawHandler;
import com.baselet.diagram.draw.DrawHandler.Layer;
import com.baselet.element.facet.Facet;
import com.baselet.element.facet.PropertiesParserState;
import com.baselet.gui.AutocompletionText;

public class NashAllInOneFacet extends Facet {

	public static final NashAllInOneFacet INSTANCE = new NashAllInOneFacet();

	private static final double Y_SPACE = 5;

	@Override
	public boolean checkStart(String line, PropertiesParserState state) {
		return true; // handle all lines
	}

	@Override
	public void handleLine(String line, PropertiesParserState state) {
		// nothing to do here
	}

	@Override
	public void parsingFinished(PropertiesParserState state, List<String> handledLines) {
		DrawHandler drawer = state.getDrawer();

		double width = 10;
		double height = 10;
		if (!checkCorrectWhile(handledLines)) {
			drawer.print("Error: Syntax for while-loop is not correct", state.getGridElementSize().width / 2.0, state.getTextPrintPosition() + drawer.getDistanceBorderToText() + drawer.textHeight("Error: Syntax for while-loop is not correct") + drawer.getDistanceBetweenTextLines(), AlignHorizontal.CENTER);
			state.updateMinimumSize(drawer.textWidth("Error: Syntax for while-loop is not correct") * 1.5, drawer.textHeight("Error: Syntax for while-loop is not correct") * 3);

			return;
		}

		for (String line : handledLines) {
			width = Math.max(width, drawer.textWidth(line));
			height += drawer.textHeight(line);

			drawer.print(line, state.getGridElementSize().width / 2.0, state.getTextPrintPosition() + drawer.getDistanceBorderToText() + drawer.textHeight(line) + drawer.getDistanceBetweenTextLines(), AlignHorizontal.CENTER);
			if (!line.startsWith("\t")) {
				drawSeperatorLine(drawer, state);
			}

			state.increaseTextPrintPosition(drawer.textHeight(line) * 3);
		}

		state.updateMinimumSize(width * 1.5, height * 1.5);
	}

	@Override
	public List<AutocompletionText> getAutocompletionStrings() {
		List<AutocompletionText> returnList = new ArrayList<AutocompletionText>();

		returnList.add(new AutocompletionText("NASH All in One", "Enter Pseudocode to generate Structogramm"));
		return returnList;
	}

	public void drawSeperatorLine(DrawHandler drawer, PropertiesParserState state) {
		LineType ltBefore = drawer.getLineType();
		drawer.setLineType(LineType.SOLID);

		drawer.setLayer(Layer.Foreground); // should be always on top of background
		double linePos = state.getTextPrintPosition() - drawer.textHeightMax() + Y_SPACE;
		XValues xPos = state.getXLimits(linePos);
		drawer.drawLine(xPos.getLeft() + 0.5, linePos, xPos.getRight() - 1, linePos);
		drawer.setLayer(Layer.Background);
		drawer.setLineType(ltBefore);
	}

	public boolean checkCorrectWhile(List<String> lines) {
		int countWhile = 0;
		int countEndwhile = 0;

		for (String line : lines) {
			if (line.trim().equalsIgnoreCase("endwhile")) {
				countEndwhile++;
			}
			if (line.trim().startsWith("while")) {
				countWhile++;
			}
		}

		return countWhile == countEndwhile;
	}
}
