package com.baselet.element.pap.facet;

import java.util.List;

import com.baselet.control.basics.geom.DimensionDouble;
import com.baselet.control.enums.AlignHorizontal;
import com.baselet.diagram.draw.DrawHandler;
import com.baselet.element.facet.PropertiesParserState;
import com.baselet.element.nash.facet.Containable;
import com.baselet.element.nash.facet.LanguageConfiguration;
import com.baselet.element.nash.facet.LanguageConfiguration.Language;
import com.baselet.element.pap.helper.PapDrawer;

public class BranchSegment implements Containable {

	private final String condition;
	private final PapContainer commandsTrue;
	private final PapContainer commandsFalse;

	private final double condition_min_width = 70;
	private final double condition_scale_factor = 25.0;
	private final double arrowLength = 40;
	private final double bottomSpacer = 10;

	private double condition_height = 0;
	private double spacer = 0;
	private double height = 0;
	private double width = 0;

	public BranchSegment(String condition, List<String> codeTrue, List<String> codeFalse) {
		this.condition = condition;
		commandsTrue = new PapContainer(codeTrue);
		commandsFalse = new PapContainer(codeFalse);
	}

	@Override
	public DimensionDouble calculateDimension(DrawHandler drawer) {
		DimensionDouble dimensionT = commandsTrue.calculateDimension(drawer);
		DimensionDouble dimensionF = commandsFalse.calculateDimension(drawer);

		spacer = dimensionT.getWidth() + dimensionF.getWidth() / 2.0 + Math.max(condition_min_width, drawer.textWidth(condition) + condition_scale_factor) / 2.0;
		width = dimensionT.getWidth() + dimensionF.getWidth() * 2.0 + spacer;
		condition_height = Math.max(condition_min_width, drawer.textWidth(condition) + condition_scale_factor);
		double tHeight = dimensionT.getHeight() + arrowLength + condition_height;
		double fHeight = dimensionF.getHeight() + arrowLength + condition_height;
		height = Math.max(tHeight, fHeight) + bottomSpacer;

		return new DimensionDouble(width, height);
	}

	@Override
	public void draw(DrawHandler drawer, PropertiesParserState state, double xOffset, double yOffset, double w) {
		PapDrawer.drawArrowDown(drawer, xOffset, yOffset, arrowLength);
		drawer.print(condition, xOffset, yOffset + arrowLength + condition_height / 2.0 + drawer.getDistanceBorderToText(), AlignHorizontal.CENTER);

		// draw Diamond
		double conditionWidth = Math.max(condition_min_width, drawer.textWidth(condition) + condition_scale_factor) / 2.0;
		drawer.drawLine(xOffset - conditionWidth, yOffset + arrowLength + condition_height / 2.0, xOffset, yOffset + arrowLength);
		drawer.drawLine(xOffset, yOffset + arrowLength, xOffset + conditionWidth, yOffset + arrowLength + condition_height / 2.0);
		drawer.drawLine(xOffset - conditionWidth, yOffset + arrowLength + condition_height / 2.0, xOffset, yOffset + arrowLength + condition_height);
		drawer.drawLine(xOffset, yOffset + arrowLength + condition_height, xOffset + conditionWidth, yOffset + arrowLength + condition_height / 2.0);

		commandsTrue.draw(drawer, state, xOffset, yOffset + arrowLength + condition_height, w);
		commandsFalse.draw(drawer, state, xOffset + spacer, yOffset + arrowLength + condition_height / 2.0, w);

		// print true and false
		if (LanguageConfiguration.INSTANCE.getLanguage() == Language.EN) {
			drawer.print("false", xOffset + conditionWidth + 15, yOffset + arrowLength + condition_height / 2.0 - drawer.getDistanceBorderToText(), AlignHorizontal.CENTER);
			drawer.print("true", xOffset + drawer.textWidth("true"), yOffset + arrowLength + condition_height + arrowLength / 2.0, AlignHorizontal.CENTER);
		}
		else if (LanguageConfiguration.INSTANCE.getLanguage() == Language.DE) {
			drawer.print("Nein", xOffset + conditionWidth + 15, yOffset + arrowLength + condition_height / 2.0 - drawer.getDistanceBorderToText(), AlignHorizontal.CENTER);
			drawer.print("Ja", xOffset + drawer.textWidth("Ja"), yOffset + arrowLength + condition_height + arrowLength / 2.0, AlignHorizontal.CENTER);
		}
		// draw connecting lines
		drawer.drawLine(xOffset + conditionWidth, yOffset + arrowLength + condition_height / 2.0, xOffset + spacer, yOffset + arrowLength + condition_height / 2.0); // to False seqeuence

		drawer.drawLine(xOffset, yOffset + arrowLength + condition_height + commandsTrue.calculateDimension(drawer).getHeight(), xOffset, yOffset + height); // from True down
		drawer.drawLine(xOffset + spacer, yOffset + arrowLength + condition_height / 2.0 + commandsFalse.calculateDimension(drawer).getHeight(), xOffset + spacer, yOffset + height); // from False down
		PapDrawer.drawArrowLeft(drawer, xOffset + spacer, yOffset + height, spacer);
	}

	@Override
	public double getHeight() {
		return height;
	}

}
