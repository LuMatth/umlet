package com.baselet.element.nash.facet;

import java.util.ArrayList;
import java.util.List;

import com.baselet.control.basics.geom.DimensionDouble;
import com.baselet.diagram.draw.DrawHandler;
import com.baselet.element.facet.Facet;
import com.baselet.element.facet.PropertiesParserState;
import com.baselet.gui.AutocompletionText;

public class NashAllInOneFacet extends Facet {

	public static final NashAllInOneFacet INSTANCE = new NashAllInOneFacet();

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

		// generate Diagram
		NashDiagram dia = new NashDiagram(handledLines);
		DimensionDouble dimension = dia.getDimension(drawer);
		state.updateMinimumSize(dimension.getWidth(), dimension.getHeight());
		dia.draw(drawer, state);
	}

	@Override
	public List<AutocompletionText> getAutocompletionStrings() {
		List<AutocompletionText> returnList = new ArrayList<AutocompletionText>();

		returnList.add(new AutocompletionText("NASH All in One", "Enter Pseudocode to generate Structogramm"));
		return returnList;
	}
}
