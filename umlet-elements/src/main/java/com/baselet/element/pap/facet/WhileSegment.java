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

public class WhileSegment implements Containable {

	private final String condition;
	private final PapContainer commands;

	private final double condition_min_width = 70;
	private final double condition_scale_factor = 25.0;
	private final double arrowLength = 40;
	private final double loopWidth = 2;
	private final double bottomSpacer = 50;

	private double condition_height = 0;
	private double height = 0;
	private double width = 0;

	public WhileSegment(String condition, List<String> code) {
		this.condition = condition;
		commands = new PapContainer(code);
	}

	@Override
	public DimensionDouble calculateDimension(DrawHandler drawer) {
		DimensionDouble dimension = commands.calculateDimension(drawer);

		width = dimension.getWidth() + Math.max(condition_min_width, drawer.textWidth(condition) + condition_scale_factor) + loopWidth;
		condition_height = Math.max(condition_min_width, drawer.textWidth(condition) + condition_scale_factor);
		height = dimension.getHeight() + arrowLength + condition_height + bottomSpacer;

		return new DimensionDouble(width * 2, height); // die Verdoppelung der Breite ist nötig, da die Diagramme in der Mitte gezeichnet werden,
														// die Breite aber von der linken Seite aus angegeben ist.
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

		// print true and false
		if (LanguageConfiguration.INSTANCE.getLanguage() == Language.EN) {
			drawer.print("false", xOffset - conditionWidth - 15, yOffset + arrowLength + condition_height / 2.0 - drawer.getDistanceBorderToText(), AlignHorizontal.CENTER);
			drawer.print("true", xOffset + drawer.textWidth("true"), yOffset + arrowLength + condition_height + arrowLength / 2.0, AlignHorizontal.CENTER);
		}
		else if (LanguageConfiguration.INSTANCE.getLanguage() == Language.DE) {
			drawer.print("Nein", xOffset - conditionWidth - 15, yOffset + arrowLength + condition_height / 2.0 - drawer.getDistanceBorderToText(), AlignHorizontal.CENTER);
			drawer.print("Ja", xOffset + drawer.textWidth("Ja"), yOffset + arrowLength + condition_height + arrowLength / 2.0, AlignHorizontal.CENTER);
		}

		commands.draw(drawer, state, xOffset, yOffset + arrowLength + condition_height, w);

		// arrow back to beginning
		drawer.drawLine(xOffset, yOffset + height - bottomSpacer * 0.8, xOffset, yOffset + height - bottomSpacer);
		drawer.drawLine(xOffset, yOffset + height - bottomSpacer * 0.8, xOffset + width, yOffset + height - bottomSpacer * 0.8);
		drawer.drawLine(xOffset + width, yOffset + height - bottomSpacer * 0.8, xOffset + width, yOffset + arrowLength / 2.0);
		PapDrawer.drawArrowLeft(drawer, xOffset + width, yOffset + arrowLength / 2.0, width);

		// arrow to the end
		drawer.drawLine(xOffset - conditionWidth, yOffset + arrowLength + condition_height / 2.0, xOffset - width, yOffset + arrowLength + condition_height / 2.0);
		drawer.drawLine(xOffset - width, yOffset + arrowLength + condition_height / 2.0, xOffset - width, yOffset + height - bottomSpacer * 0.2);
		drawer.drawLine(xOffset - width, yOffset + height - bottomSpacer * 0.2, xOffset, yOffset + height - bottomSpacer * 0.2);
		drawer.drawLine(xOffset, yOffset + height - bottomSpacer * 0.2, xOffset, yOffset + height);

	}

	@Override
	public double getHeight() {
		return height;
	}

}
