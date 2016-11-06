package com.baselet.element.nash;

import com.baselet.control.enums.ElementId;
import com.baselet.element.NewGridElement;
import com.baselet.element.facet.PropertiesParserState;
import com.baselet.element.facet.Settings;
import com.baselet.element.nash.facet.SettingsNashLoop;

public class WhileLoop extends NewGridElement {

	@Override
	public ElementId getId() {
		return ElementId.NASHWhileLoop;
	}

	@Override
	protected void drawCommonContent(PropertiesParserState state) {
		// TODO Auto-generated method stub

	}

	@Override
	protected Settings createSettings() {
		return new SettingsNashLoop();
	}

}
