package com.baselet.element.pap.facet;

import java.util.List;

import com.baselet.control.basics.geom.DimensionDouble;
import com.baselet.control.enums.AlignHorizontal;
import com.baselet.diagram.draw.DrawHandler;
import com.baselet.element.facet.PropertiesParserState;
import com.baselet.element.language.LanguageConfiguration;
import com.baselet.element.language.LanguageConfiguration.Language;
import com.baselet.element.nash.facet.Containable;
import com.baselet.element.pap.helper.PapDrawer;

public class DoWhileSegment implements Containable {

	private final String condition;
	private final PapContainer commands;

	private final double condition_min_width = 70;
	private final double condition_scale_factor = 25.0;
	private final double arrowLength = 40;
	private final double loopWidth = 2;

	private double condition_height = 0;
	private double height = 0;
	private double width = 0;

	public DoWhileSegment(String condition, List<String> code) {
		this.condition = condition;
		commands = new PapContainer(code);
	}

	@Override
	public DimensionDouble calculateDimension(DrawHandler drawer) {
		DimensionDouble dimension = commands.calculateDimension(drawer);

		width = dimension.getWidth() + Math.max(condition_min_width, drawer.textWidth(condition) + condition_scale_factor) + loopWidth;
		condition_height = Math.max(condition_min_width, drawer.textWidth(condition) + condition_scale_factor);
		height = dimension.getHeight() + arrowLength + condition_height;

		return new DimensionDouble(width, height);
	}

	@Override
	public void draw(DrawHandler drawer, PropertiesParserState state, double xOffset, double yOffset, double w) {
		commands.draw(drawer, state, xOffset, yOffset, w);

		PapDrawer.drawArrowDown(drawer, xOffset, yOffset + height - condition_height - arrowLength, arrowLength);
		drawer.print(condition, xOffset, yOffset + height - condition_height / 2.0 + drawer.getDistanceBorderToText(), AlignHorizontal.CENTER);

		// draw Diamond
		double conditionWidth = Math.max(condition_min_width, drawer.textWidth(condition) + condition_scale_factor) / 2.0;
		drawer.drawLine(xOffset - conditionWidth, yOffset + height - condition_height / 2.0, xOffset, yOffset + height - condition_height);
		drawer.drawLine(xOffset, yOffset + height - condition_height, xOffset + conditionWidth, yOffset + height - condition_height / 2.0);
		drawer.drawLine(xOffset - conditionWidth, yOffset + height - condition_height / 2.0, xOffset, yOffset + height);
		drawer.drawLine(xOffset, yOffset + height, xOffset + conditionWidth, yOffset + height - condition_height / 2.0);

		// draw arrow to the start of the loop
		drawer.drawLine(xOffset + conditionWidth, yOffset + height - condition_height / 2.0, xOffset + width, yOffset + height - condition_height / 2.0);
		drawer.drawLine(xOffset + width, yOffset + height - condition_height / 2.0, xOffset + width, yOffset + arrowLength / 2.0);
		PapDrawer.drawArrowLeft(drawer, xOffset + width, yOffset + arrowLength / 2.0, width);

		// print true and false
		if (LanguageConfiguration.INSTANCE.getLanguage() == Language.EN) {
			drawer.print("true", xOffset + Math.max(condition_min_width, drawer.textWidth(condition) + condition_scale_factor) + drawer.getDistanceBorderToText(), yOffset + height - condition_height / 2.0, AlignHorizontal.CENTER);
			drawer.print("false", xOffset + drawer.textWidth("false") / 2.0, yOffset + height + arrowLength / 3.0, AlignHorizontal.CENTER);
		}
		else if (LanguageConfiguration.INSTANCE.getLanguage() == Language.DE) {
			drawer.print("Ja", xOffset + Math.max(condition_min_width, drawer.textWidth(condition) + condition_scale_factor) + drawer.getDistanceBorderToText(), yOffset + height - condition_height / 2.0, AlignHorizontal.CENTER);
			drawer.print("Nein", xOffset + drawer.textWidth("falsch") / 2.0, yOffset + height + arrowLength / 3.0, AlignHorizontal.CENTER);
		}
	}

	@Override
	public double getHeight() {
		return height;
	}

}
