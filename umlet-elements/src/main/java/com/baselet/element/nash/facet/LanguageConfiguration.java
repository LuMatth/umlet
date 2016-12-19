package com.baselet.element.nash.facet;

public class LanguageConfiguration {

	public enum Language {
		DE, EN
	}

	public static final LanguageConfiguration INSTANCE = new LanguageConfiguration();

	private Language language;

	private LanguageConfiguration() {
		language = Language.DE;
	}

	public void setLanguage(Language lang) {
		language = lang;
	}

	public Language getLanguage() {
		return language;
	}
}
