package de.ifheroes.core.profile.types;

public enum HeroProfileLanguage implements HeroProfileType{

	DE("Deutsch"), EN("English");
	
	private String fullName;
	
	private HeroProfileLanguage(String fullName) {
		this.fullName = fullName;
	}
	
	public String getFullName() {
		return this.fullName;
	}
	
}
