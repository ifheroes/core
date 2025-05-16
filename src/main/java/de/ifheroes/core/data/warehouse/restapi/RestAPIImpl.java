package de.ifheroes.core.data.warehouse.restapi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.nio.charset.StandardCharsets;

<<<<<<< Updated upstream:src/main/java/de/ifheroes/core/warehouse/restapi/RestAPIImpl.java
import de.ifheroes.core.Logger;
import de.ifheroes.core.Logger.LogLevel;
import de.ifheroes.core.warehouse.exceptions.DeleteRequestFailedException;
import de.ifheroes.core.warehouse.exceptions.GetRequestFailedException;
import de.ifheroes.core.warehouse.exceptions.PostRequestFailedException;
=======
import org.bukkit.Bukkit;

import de.ifheroes.core.data.warehouse.exceptions.DeleteRequestFailedException;
import de.ifheroes.core.data.warehouse.exceptions.GetRequestFailedException;
import de.ifheroes.core.data.warehouse.exceptions.PostRequestFailedException;
>>>>>>> Stashed changes:src/main/java/de/ifheroes/core/data/warehouse/restapi/RestAPIImpl.java

/*
 * Adjust HTTP Request Timeout to API waiting times
 */

/**
 * This class provides methods to interact with a REST API, allowing the sending of GET and POST requests.
 * It handles authorization and response management, throwing custom exceptions when requests fail.
 */
public class RestAPIImpl implements RestAPI{
	
	private String baseUrl;
    private String token;
    
	private static final int TIMEOUTCONNECTION = 1000000;
	
	/**
	 * Constructor for the RestAPI class.
	 * 
	 * @param baseUrl The base URL of the REST API.
	 * @param token   The authorization token used for API requests.
	 */
	public RestAPIImpl(String baseUrl, String token) {
		this.baseUrl = baseUrl;
		this.token = token;
	}

	/**
	 * Sends a GET request to the specified endpoint of the REST API.
	 * 
	 * @param endpoint The specific endpoint to which the GET request is sent.
	 * @return The response from the API as a String.
	 * @throws IOException               If an input or output exception occurs.
	 * @throws GetRequestFailedException If the GET request fails and returns an
	 *                                   HTTP error code.
	 */
	@Override
	public String sendGetRequest(String endpoint) throws IOException, GetRequestFailedException {
		String url = buildUUIDUrl(endpoint);
		HttpURLConnection connection = createHttpConnection(url, HttpMethode.GET);
		
		if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
			return extractReponse(connection);
		} else {
			throw new GetRequestFailedException(endpoint, connection.getResponseCode());
		}
	}

	private String buildUUIDUrl(String uuid) {
		return baseUrl + "?uuid=" + uuid;
	}
	
	private String buildDELETEUrl(String uuid) {
		return baseUrl + "?delete=" + uuid;
	}
	
	private String extractReponse(HttpURLConnection httpConnection) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(httpConnection.getInputStream()));
		String inputLine;
		StringBuilder response = new StringBuilder();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();
		return response.toString();
	}
	
	/**
	 * Sends a POST request with JSON data to the specified endpoint of the REST
	 * API.
	 * 
	 * @param endpoint        The specific endpoint to which the POST request is
	 *                        sent.
	 * @param jsonInputString The JSON input string to be sent in the POST request
	 *                        body.
	 * @return true if the POST request was successful, false otherwise.
	 * @throws IOException                If an input or output exception occurs.
	 * @throws PostRequestFailedException If the POST request fails and returns an
	 *                                    HTTP error code.
	 */
	@Override
	public boolean sendPostRequest(String endpoint, String jsonInputString)
			throws IOException, PostRequestFailedException {
		
		long startmilis = System.currentTimeMillis();
		String url = baseUrl;
		HttpURLConnection connection = createHttpConnection(url, HttpMethode.POST);
		
		try (OutputStream os = connection.getOutputStream()) {
			byte[] input = jsonInputString.getBytes(StandardCharsets.UTF_8);
			os.write(input, 0, input.length);
		}

		int responseCode = connection.getResponseCode();
		if (responseCode == HttpURLConnection.HTTP_OK || responseCode == HttpURLConnection.HTTP_CREATED) {
			new Logger(LogLevel.INFO).info("PostRequest: %s".formatted(System.currentTimeMillis()-startmilis));
			return true;
		} else {
			throw new PostRequestFailedException(endpoint, jsonInputString, responseCode);
		}
	}

	@Override
	public boolean sendDeleteRequest(String endpoint) throws IOException, DeleteRequestFailedException {
		String url = buildDELETEUrl(endpoint);
		HttpURLConnection connection = createHttpConnection(url, HttpMethode.DELETE);
		
		if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
			return true;
		} else {
			throw new DeleteRequestFailedException(endpoint, connection.getResponseCode());
		}
	}

	private enum HttpMethode {
		GET, POST, DELETE;
	}
	
	private HttpURLConnection createHttpConnection(String url, HttpMethode httpMethode) throws IOException {
		HttpURLConnection connection = (HttpURLConnection) URI.create(url).toURL().openConnection();
		connection.setRequestMethod(httpMethode.name());
		connection.setRequestProperty("Authorization", token);
		
		
		connection.setConnectTimeout(TIMEOUTCONNECTION);
		
		if(httpMethode == HttpMethode.POST) {
			connection.setRequestProperty("Content-Type", "application/json; utf-8");
			connection.setDoOutput(true);
		}
		
		
		return connection;
	}
}
