package com.baselet.element.nash.facet;

import java.util.ArrayList;
import java.util.List;

import com.baselet.control.basics.XValues;
import com.baselet.control.basics.geom.Dimension;
import com.baselet.control.enums.AlignHorizontal;
import com.baselet.control.enums.LineType;
import com.baselet.diagram.draw.DrawHandler;
import com.baselet.diagram.draw.DrawHandler.Layer;
import com.baselet.element.facet.Facet;
import com.baselet.element.facet.PropertiesParserState;
import com.baselet.gui.AutocompletionText;

public class BranchLineFacet extends Facet {

	public static final BranchLineFacet INSTANCE = new BranchLineFacet();

	private static final String KEY_PREFIX = "\\/";

	private static final double Y_SPACE = 5;

	@Override
	public boolean checkStart(String line, PropertiesParserState state) {
		return line.equals(KEY_PREFIX);
	}

	@Override
	public void handleLine(String line, PropertiesParserState state) {
		DrawHandler drawer = state.getDrawer();
		LineType ltBefore = drawer.getLineType();

		drawer.setLineType(LineType.SOLID);
		drawer.setLayer(Layer.Foreground); // should be always on top of background

		// double 0.0 = state.getTextPrintPosition() - drawer.textHeightMax() + Y_SPACE / 2;
		Dimension d = state.getGridElementSize();
		XValues xPos = state.getXLimits(0.0);
		drawer.drawLine(xPos.getLeft() + 0.5, 0.0, xPos.getRight() / 2.0f, d.getHeight());
		drawer.drawLine(xPos.getRight() / 2.0f, d.getHeight(), xPos.getRight() - 1, 0.0);
		state.increaseTextPrintPosition(Y_SPACE);

		drawer.print("Ja", xPos.getLeft() + 2.5, d.getHeight() - 3.5, AlignHorizontal.LEFT);
		drawer.print("Nein", xPos.getRight() - 3, d.getHeight() - 3.5, AlignHorizontal.RIGHT);

		drawer.setLayer(Layer.Background);
		drawer.setLineType(ltBefore);

	}

	@Override
	public List<AutocompletionText> getAutocompletionStrings() {
		List<AutocompletionText> returnList = new ArrayList<AutocompletionText>();

		returnList.add(new AutocompletionText(BranchLineFacet.KEY_PREFIX, "Draw NASH branch line"));

		return returnList;
	}

}
