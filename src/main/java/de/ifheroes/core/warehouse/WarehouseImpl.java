package de.ifheroes.core.warehouse;

import java.io.IOException;
import java.util.Optional;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import de.ifheroes.core.warehouse.exceptions.GetRequestFailedException;
import de.ifheroes.core.warehouse.exceptions.PostRequestFailedException;
import de.ifheroes.core.warehouse.restapi.RestAPI;
import de.ifheroes.core.warehouse.restapi.RestAPIImpl;

/*
 * Represents the implementation of the Warehouse
 * for further information and usage of the methods please take a look at the Warehouse interface
 */
public class WarehouseImpl implements Warehouse {

	private RestAPI restAPI;
	
	public WarehouseImpl(String url, String restAPIkey) {
		restAPI = new RestAPIImpl(url, restAPIkey);
	}
	
	@Override
	public Optional<JsonObject> get(String key) throws GetRequestFailedException {
		String result = "";
		try {
			result = restAPI.sendGetRequest(key);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return Optional.of(new Gson().fromJson(result, JsonObject.class));
	}

	@Override
	public void post(String key, PostRequestBody body) {
		try {
			restAPI.sendPostRequest(key, new Gson().toJson(body));
		} catch (IOException | PostRequestFailedException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void delete(String key) {
		// TODO Auto-generated method stub
	}
}
