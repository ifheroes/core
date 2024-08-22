package de.ifheroes.core.warehouse.restapi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.nio.charset.StandardCharsets;

import de.ifheroes.core.warehouse.exceptions.GetRequestFailedException;
import de.ifheroes.core.warehouse.exceptions.PostRequestFailedException;

/**
 * This class provides methods to interact with a REST API, allowing the sending of GET and POST requests.
 * It handles authorization and response management, throwing custom exceptions when requests fail.
 */
public class RestAPI {

	/*
	 * Probably needs a interface
	 */
	
	private String baseUrl;
    private String token;

    /**
     * Constructor for the RestAPI class.
     * 
     * @param baseUrl The base URL of the REST API.
     * @param token The authorization token used for API requests.
     */
    public RestAPI(String baseUrl, String token) {
        this.baseUrl = baseUrl;
        this.token = token;
    }
    
    /**
     * Sends a GET request to the specified endpoint of the REST API.
     * 
     * @param endpoint The specific endpoint to which the GET request is sent.
     * @return The response from the API as a String.
     * @throws IOException If an input or output exception occurs.
     * @throws GetRequestFailedException If the GET request fails and returns an HTTP error code.
     */
    public String sendGetRequest(String endpoint) throws IOException, GetRequestFailedException {
        String url = baseUrl + endpoint;
        
        HttpURLConnection connection = (HttpURLConnection) URI.create(url).toURL().openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Authorization", token);

        int responseCode = connection.getResponseCode(); 
        
        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            return response.toString();
        } else {
        	throw new GetRequestFailedException(endpoint, responseCode);
        }
    }

    /**
     * Sends a POST request with JSON data to the specified endpoint of the REST API.
     * 
     * @param endpoint The specific endpoint to which the POST request is sent.
     * @param jsonInputString The JSON input string to be sent in the POST request body.
     * @return true if the POST request was successful, false otherwise.
     * @throws IOException If an input or output exception occurs.
     * @throws PostRequestFailedException If the POST request fails and returns an HTTP error code.
     */
    public boolean sendPostRequest(String endpoint, String jsonInputString) throws IOException, PostRequestFailedException {
        String url = baseUrl + endpoint;
        HttpURLConnection connection = (HttpURLConnection) URI.create(url).toURL().openConnection();
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Authorization", token);
        connection.setRequestProperty("Content-Type", "application/json; utf-8");
        connection.setDoOutput(true);

        try (OutputStream os = connection.getOutputStream()) {
            byte[] input = jsonInputString.getBytes(StandardCharsets.UTF_8);
            os.write(input, 0, input.length);
        }

        int responseCode = connection.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK || responseCode == HttpURLConnection.HTTP_CREATED) {
        	return true;
        } else {
        	throw new PostRequestFailedException(endpoint, jsonInputString, responseCode);
        }
    }
}
