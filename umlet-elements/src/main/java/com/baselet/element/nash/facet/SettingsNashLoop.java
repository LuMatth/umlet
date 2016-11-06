package com.baselet.element.nash.facet;

import java.util.List;

import com.baselet.control.enums.ElementStyle;
import com.baselet.element.facet.Facet;
import com.baselet.element.facet.Settings;

public class SettingsNashLoop extends Settings {

	@Override
	public ElementStyle getElementStyle() {
		return ElementStyle.SIMPLE;
	}

	@Override
	protected List<Facet> createFacets() {
		return listOf(Settings.MANUALRESIZE, WhileFacet.INSTANCE, DoWhileFacet.INSTANCE);
	}

}
