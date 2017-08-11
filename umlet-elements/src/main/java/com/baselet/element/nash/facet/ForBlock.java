package com.baselet.element.nash.facet;

import java.util.List;

import com.baselet.control.basics.geom.DimensionDouble;
import com.baselet.control.enums.AlignHorizontal;
import com.baselet.diagram.draw.DrawHandler;
import com.baselet.element.facet.PropertiesParserState;
import com.baselet.element.language.LanguageConfiguration;
import com.baselet.element.language.LanguageConfiguration.Language;

public class ForBlock implements Containable {
	private String condition;
	private final NashContainer commands;

	private final double drawHeight = 45.0;
	private final double drawWidth = 45.0;

	private double height = 0;

	public ForBlock(String condition, List<String> code) {
		this.condition = condition;
		commands = new NashContainer(code);
	}

	@Override
	public DimensionDouble calculateDimension(DrawHandler drawer) {
		DimensionDouble dimension = commands.calculateDimension(drawer);

		double width = dimension.getWidth() + drawWidth;
		height = dimension.getHeight() + drawHeight;

		return new DimensionDouble(width, height);
	}

	@Override
	public void draw(DrawHandler drawer, PropertiesParserState state, double xOffset, double yOffset, double width) {
		drawer.drawLine(xOffset, yOffset, xOffset, yOffset + height);
		drawer.drawLine(xOffset, yOffset, width, yOffset);
		drawer.drawLine(width, yOffset, width, yOffset + drawHeight);
		drawer.drawLine(width, yOffset + drawHeight, xOffset + drawWidth, yOffset + drawHeight);
		drawer.drawLine(xOffset + drawWidth, yOffset + drawHeight, xOffset + drawWidth, yOffset + height);
		drawer.drawLine(xOffset + drawWidth, yOffset + height, xOffset, yOffset + height);

		if (LanguageConfiguration.INSTANCE.getLanguage() == Language.EN) {}
		else if (LanguageConfiguration.INSTANCE.getLanguage() == Language.DE) {
			condition = condition.replace("for", "für");
		}
		drawer.print(condition, xOffset + drawWidth / 2.0, yOffset + drawer.getDistanceBorderToText() * 2 + drawer.textHeight(condition) + drawer.getDistanceBetweenTextLines(), AlignHorizontal.LEFT);
		commands.draw(drawer, state, xOffset + drawWidth, yOffset + drawHeight, width);

	}

	@Override
	public double getHeight() {
		return height;
	}

}
