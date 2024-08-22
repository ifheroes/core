package de.ifheroes.core.warehouse;

import java.util.Optional;

import com.google.gson.JsonObject;

/*
 * Represents the usage of the InfinityHeroes-Warehouse
 */
public interface Warehouse {
	
	/**
	 * Gets the Optional<JsonObject> from the Warehouse REST API
	 * 
	 * @param key is the key being used to get the JSONObject
	 * @return Optional<JsonObject>
	 */
	public Optional<JsonObject> get(String key);
	
	/**
	 * Sets the given key and the data to it in the Warehouse over the REST API
	 * 
	 * @param key is being used as the key
	 * @param value is being used as the value
	 *
	 */
	public void set(String key, JsonObject value);
	
	
	/**
	 * Removes the Data of the Key from the warehouse
	 * @param key is being used for the deletion  of its data
	 *
	 */
	public void delete(String key);
	
}
