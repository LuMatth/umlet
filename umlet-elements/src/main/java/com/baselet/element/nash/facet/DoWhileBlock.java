package com.baselet.element.nash.facet;

import java.util.List;

import com.baselet.control.basics.geom.DimensionDouble;
import com.baselet.control.enums.AlignHorizontal;
import com.baselet.diagram.draw.DrawHandler;
import com.baselet.element.facet.PropertiesParserState;
import com.baselet.element.nash.facet.LanguageConfiguration.Language;

public class DoWhileBlock implements Containable {

	private String condition;
	private final NashContainer commands;

	private final double drawHeight = 45.0;
	private final double drawWidth = 45.0;

	private double height = 0;

	public DoWhileBlock(String condition, List<String> code) {
		this.condition = condition;
		commands = new NashContainer(code);
	}

	@Override
	public DimensionDouble calculateDimension(DrawHandler drawer) {
		DimensionDouble dimension = commands.calculateDimension(drawer);

		double width = dimension.getWidth() + drawWidth + drawer.textWidth(condition.replace("while", "solange"));
		height = dimension.getHeight() + drawHeight;

		return new DimensionDouble(width, height);
	}

	@Override
	public void draw(DrawHandler drawer, PropertiesParserState state, double xOffset, double yOffset, double width) {
		drawer.drawLine(xOffset, yOffset, xOffset, yOffset + height);
		drawer.drawLine(xOffset, yOffset, xOffset + drawWidth, yOffset);
		drawer.drawLine(xOffset + drawWidth, yOffset, xOffset + drawWidth, yOffset + height - drawHeight);
		drawer.drawLine(xOffset + drawWidth, yOffset + height - drawHeight, width, yOffset + height - drawHeight);
		drawer.drawLine(xOffset, yOffset + height, width, yOffset + height);
		drawer.drawLine(width, yOffset + height - drawHeight, width, yOffset + height);

		// print Do and While
		if (LanguageConfiguration.INSTANCE.getLanguage() == Language.EN) {
			drawer.print(condition, xOffset + drawWidth / 2.0, yOffset + height - drawHeight + drawer.getDistanceBorderToText() * 2 + drawer.textHeight(condition) + drawer.getDistanceBetweenTextLines(), AlignHorizontal.LEFT);
			drawer.print("do", xOffset + drawWidth / 3.0, yOffset + drawer.getDistanceBorderToText() + drawer.textHeight("do") + drawer.getDistanceBetweenTextLines() + height / 3.0, AlignHorizontal.LEFT);
		}
		else if (LanguageConfiguration.INSTANCE.getLanguage() == Language.DE) {
			condition = condition.replace("while", "solange");
			drawer.print(condition, xOffset + drawWidth / 2.0, yOffset + height - drawHeight + drawer.getDistanceBorderToText() * 2 + drawer.textHeight(condition) + drawer.getDistanceBetweenTextLines(), AlignHorizontal.LEFT);
			drawer.print("tue", xOffset + drawWidth / 3.0, yOffset + drawer.getDistanceBorderToText() + drawer.textHeight("do") + drawer.getDistanceBetweenTextLines() + height / 3.0, AlignHorizontal.LEFT);

		}
		commands.draw(drawer, state, xOffset + drawWidth, yOffset, width);

	}

	@Override
	public double getHeight() {
		return height;
	}

}
