package de.ifheroes.core.profile.types;

import com.google.gson.JsonObject;

/*
 * Represents a saveable data format
 */
public interface HeroProfileType{

	/*
	 * Creates a JSON String from the Object
	 */
	public JsonObject toJson();
	
}
