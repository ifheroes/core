package de.ifheroes.core.profile.types;

public enum HeroProfileLanguage {

	DE("Deutsch"), EN("English");
	
	private String fullName;
	
	private HeroProfileLanguage(String fullName) {
		this.fullName = fullName;
	}
	
	public String getFullName() {
		return this.fullName;
	}
}
