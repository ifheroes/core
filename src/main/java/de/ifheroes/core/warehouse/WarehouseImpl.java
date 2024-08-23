package de.ifheroes.core.warehouse;

import java.io.IOException;
import java.util.Optional;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

import de.ifheroes.core.warehouse.exceptions.GetRequestFailedException;
import de.ifheroes.core.warehouse.restapi.RestAPI;
import de.ifheroes.core.warehouse.restapi.RestAPIImpl;

/*
 * Represents the implementation of the Warehouse
 * for further information and usage of the methods please take a look at the Warehouse interface
 */
public class WarehouseImpl implements Warehouse {

	private RestAPI restAPI;
	
	public WarehouseImpl(String url, String endpoint, String restAPIkey) {
		restAPI = new RestAPIImpl(url+"/"+endpoint, restAPIkey);
	}
	
	@Override
	public Optional<JsonObject> get(String key) {
		String result = "";
		try {
			result = restAPI.sendGetRequest(key);
		} catch (IOException | GetRequestFailedException e) {
			e.printStackTrace();
		}
		return Optional.of(new Gson().fromJson(result, JsonObject.class));
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
