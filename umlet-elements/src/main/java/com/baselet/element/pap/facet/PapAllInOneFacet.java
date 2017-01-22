package com.baselet.element.pap.facet;

import java.util.ArrayList;
import java.util.List;

import com.baselet.control.basics.geom.DimensionDouble;
import com.baselet.diagram.draw.DrawHandler;
import com.baselet.element.facet.Facet;
import com.baselet.element.facet.PropertiesParserState;
import com.baselet.gui.AutocompletionText;

public class PapAllInOneFacet extends Facet {

	public static final PapAllInOneFacet INSTANCE = new PapAllInOneFacet();

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
		PapDiagram dia = new PapDiagram(handledLines);
		DimensionDouble dimension = dia.getDimension(drawer);
		state.updateMinimumSize(dimension.getWidth(), dimension.getHeight());
		dia.draw(drawer, state);
	}

	@Override
	public List<AutocompletionText> getAutocompletionStrings() {
		List<AutocompletionText> returnList = new ArrayList<AutocompletionText>();

		returnList.add(new AutocompletionText("PAP All in One", "Enter Pseudocode to generate flowchart"));
		return returnList;
	}

}
