package de.ifheroes.core.warehouse;

import java.util.Optional;

import com.google.gson.JsonObject;

/*
 * Represents the usage of the ifheroes warehouse
 */
public interface Warehouse {

	public Optional<JsonObject> get(String key);
	public Optional<JsonObject> get(String key, String attributes);
	public void set(String key, JsonObject value);
	
	
	/*
	 * Might not be used
	 */
	public void update(String key, JsonObject value);
	public void delete(String key);
	
}
