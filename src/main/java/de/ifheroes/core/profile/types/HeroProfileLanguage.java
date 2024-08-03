package de.ifheroes.core.profile.types;

import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

public enum HeroProfileLanguage implements HeroProfileType{

	DE("Deutsch"), EN("English");
	
	private String fullName;
	
	private HeroProfileLanguage(String fullName) {
		this.fullName = fullName;
	}
	
	public String getFullName() {
		return this.fullName;
	}

	@Override
	public JsonObject toJson() {
		return new JsonPrimitive(fullName).getAsJsonObject();
	}
	
}
