package com.baselet.element.language;

import com.baselet.element.facet.FirstRunKeyValueFacet;
import com.baselet.element.facet.PropertiesParserState;
import com.baselet.element.language.LanguageConfiguration.Language;

public class LanguageFacet extends FirstRunKeyValueFacet {

	public static final LanguageFacet INSTANCE = new LanguageFacet();

	@Override
	public KeyValue getKeyValue() {
		return new KeyValue("lang", false, "en", "Language for Displaying Structograms (de,en)");
	}

	@Override
	public void handleValue(String value, PropertiesParserState state) {
		if (value.equalsIgnoreCase("de")) {
			LanguageConfiguration.INSTANCE.setLanguage(Language.DE);
		}
		else if (value.equalsIgnoreCase("en")) {
			LanguageConfiguration.INSTANCE.setLanguage(Language.EN);
		}
		else {
			// do not throw exception for now
			// throw new StyleException("value must be \"de\" or \"en\"");
		}

	}

}
