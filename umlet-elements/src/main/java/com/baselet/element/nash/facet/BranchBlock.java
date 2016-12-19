package com.baselet.element.nash.facet;

import java.util.List;

import com.baselet.control.basics.geom.DimensionDouble;
import com.baselet.control.enums.AlignHorizontal;
import com.baselet.diagram.draw.DrawHandler;
import com.baselet.element.facet.PropertiesParserState;
import com.baselet.element.nash.facet.LanguageConfiguration.Language;

public class BranchBlock implements Containable {
	private final String condition;
	private final NashContainer commandsTrue;
	private final NashContainer commandsFalse;

	private final double drawHeight = 45.0;
	private double height;

	public BranchBlock(String condition, List<String> codeTrue, List<String> codeFalse) {
		this.condition = condition;
		commandsTrue = new NashContainer(codeTrue);
		commandsFalse = new NashContainer(codeFalse);
	}

	@Override
	public DimensionDouble calculateDimension(DrawHandler drawer) {
		DimensionDouble left, right;
		double width;

		width = drawer.textWidth(condition) * 3;

		left = commandsTrue.calculateDimension(drawer);
		right = commandsFalse.calculateDimension(drawer);

		width = Math.max(width, Math.max(left.getWidth() * 2, right.getWidth() * 2));
		height = Math.max(left.getHeight(), right.getHeight()) + drawHeight;
		return new DimensionDouble(width, height);
	}

	@Override
	public void draw(DrawHandler drawer, PropertiesParserState state, double xOffset, double yOffset, double width) {
		// draw rectangle (for vertical lines use whole height to cover count differences between left and right container!
		drawer.drawLine(xOffset, yOffset, width, yOffset);
		drawer.drawLine(xOffset, yOffset + drawHeight, width, yOffset + drawHeight);
		drawer.drawLine(xOffset, yOffset, xOffset, yOffset + height);
		drawer.drawLine(width, yOffset, width, yOffset + height);
		drawer.drawLine(xOffset, yOffset + height, width, yOffset + height);

		// draw branchline
		drawer.drawLine(xOffset, yOffset, width - (width - xOffset) / 2.0, yOffset + drawHeight);
		drawer.drawLine(width - (width - xOffset) / 2.0, yOffset + drawHeight, width, yOffset);

		// print condition and true/false
		drawer.print(condition, xOffset + (width - xOffset) / 2, yOffset + drawer.getDistanceBorderToText() + drawer.getDistanceBetweenTextLines() + drawer.textHeight(condition), AlignHorizontal.CENTER);

		if (LanguageConfiguration.INSTANCE.getLanguage() == Language.EN) {
			drawer.print("true", xOffset + 20.0, yOffset + drawHeight - 10.0, AlignHorizontal.CENTER);
			drawer.print("false", width - 20.0, yOffset + drawHeight - 10.0, AlignHorizontal.CENTER);
		}
		else if (LanguageConfiguration.INSTANCE.getLanguage() == Language.DE) {
			drawer.print("Ja", xOffset + 20.0, yOffset + drawHeight - 10.0, AlignHorizontal.CENTER);
			drawer.print("Nein", width - 20.0, yOffset + drawHeight - 10.0, AlignHorizontal.CENTER);
		}

		// print right and left Statements
		commandsTrue.draw(drawer, state, xOffset, yOffset + drawHeight, width - (width - xOffset) / 2);
		commandsFalse.draw(drawer, state, xOffset + (width - xOffset) / 2.0, yOffset + drawHeight, width);
	}

	@Override
	public double getHeight() {
		return height;
	}

}
