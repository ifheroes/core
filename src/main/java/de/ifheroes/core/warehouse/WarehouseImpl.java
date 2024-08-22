package de.ifheroes.core.warehouse;

import java.util.Optional;

import com.google.gson.JsonObject;

/*
 * Represents the implementation of the Warehouse
 * for further information and usage of the methods please take a look at the Warehouse interface
 */
public class WarehouseImpl implements Warehouse {

	private String url;
	private String restAPIKey;
	
	public WarehouseImpl(String url, String restAPIkey) {
		this.url= url;
		this.restAPIKey = restAPIkey;
	}
	
	@Override
	public Optional<JsonObject> get(String key) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public void set(String key, JsonObject value) {
		// TODO Auto-generated method stub
	}

	@Override
	public void delete(String key) {
		// TODO Auto-generated method stub
	}
}
