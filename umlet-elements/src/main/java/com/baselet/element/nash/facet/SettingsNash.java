package com.baselet.element.nash.facet;

import java.util.List;

import com.baselet.control.enums.ElementStyle;
import com.baselet.element.facet.Facet;
import com.baselet.element.facet.Settings;
import com.baselet.element.facet.common.SeparatorLineFacet;
import com.baselet.element.facet.specific.TemplateClassFacet;

public class SettingsNash extends Settings {

	@Override
	public ElementStyle getElementStyle() {
		return ElementStyle.SIMPLE;
	}

	@Override
	protected List<Facet> createFacets() {
		// return Settings.NASH;
		return listOf(Settings.MANUALRESIZE, SeparatorLineFacet.INSTANCE, BranchLineFacet.INSTANCE, TemplateClassFacet.INSTANCE);
	}
}
