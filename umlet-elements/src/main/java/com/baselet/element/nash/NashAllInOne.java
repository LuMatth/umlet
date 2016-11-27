package com.baselet.element.nash;

import java.util.List;

import com.baselet.control.enums.AlignHorizontal;
import com.baselet.control.enums.ElementId;
import com.baselet.control.enums.ElementStyle;
import com.baselet.element.NewGridElement;
import com.baselet.element.facet.Facet;
import com.baselet.element.facet.PropertiesParserState;
import com.baselet.element.facet.Settings;
import com.baselet.element.facet.common.FontSizeFacet;
import com.baselet.element.facet.customdrawings.CustomDrawingFacet;
import com.baselet.element.facet.specific.TemplateClassFacet;
import com.baselet.element.nash.facet.NashAllInOneFacet;

public class NashAllInOne extends NewGridElement {

	@Override
	public ElementId getId() {
		return ElementId.NASHAllInOne;
	}

	@Override
	protected void drawCommonContent(PropertiesParserState state) {
		// TODO Auto-generated method stub

	}

	@Override
	protected Settings createSettings() {
		return new Settings() {
			@Override
			public AlignHorizontal getHAlign() {
				// use left alignment because with center the autoresize would always jump 2 grid cells
				return AlignHorizontal.LEFT;
			}

			@Override
			public ElementStyle getElementStyle() {
				return ElementStyle.AUTORESIZE;
			}

			@Override
			protected List<Facet> createFacets() {
				return listOf(Settings.BASE, FontSizeFacet.INSTANCE, CustomDrawingFacet.INSTANCE, NashAllInOneFacet.INSTANCE, TemplateClassFacet.INSTANCE);
			}
		};
	}

}
