package de.ifheroes.core.warehouse;

import java.util.Optional;

import com.google.gson.JsonObject;

import de.ifheroes.core.warehouse.exceptions.GetRequestFailedException;

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
	public Optional<JsonObject> get(String key) throws GetRequestFailedException;
	
	/**
	 * Sets the given key and the data to it in the Warehouse over the REST API
	 * 
	 * @param key is being used as the key
	 * @param body is being used as the body
	 *
	 */
	public void post(String key, PostRequestBody body);
	
	
	/**
	 * Removes the Data of the Key from the warehouse
	 * @param key is being used for the deletion  of its data
	 *
	 */
	public void delete(String key);
	
}
