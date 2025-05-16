package de.ifheroes.core.data.warehouse.restapi;

import java.io.IOException;

import de.ifheroes.core.data.warehouse.exceptions.DeleteRequestFailedException;
import de.ifheroes.core.data.warehouse.exceptions.GetRequestFailedException;
import de.ifheroes.core.data.warehouse.exceptions.PostRequestFailedException;

/**
 * This interface defines methods to interact with a REST API, allowing the sending of GET and POST requests.
 */
public interface RestAPI {

    /**
     * Sends a GET request to the specified endpoint of the REST API.
     * 
     * @param endpoint The specific endpoint to which the GET request is sent.
     * @return The response from the API as a String.
     * @throws IOException If an input or output exception occurs.
     * @throws GetRequestFailedException If the GET request fails and returns an HTTP error code.
     */
    String sendGetRequest(String endpoint) throws IOException, GetRequestFailedException;

    /**
     * Sends a POST request with JSON data to the specified endpoint of the REST API.
     * 
     * @param endpoint The specific endpoint to which the POST request is sent.
     * @param jsonInputString The JSON input string to be sent in the POST request body.
     * @return true if the POST request was successful, false otherwise.
     * @throws IOException If an input or output exception occurs.
     * @throws PostRequestFailedException If the POST request fails and returns an HTTP error code.
     */
    boolean sendPostRequest(String endpoint, String jsonInputString) throws IOException, PostRequestFailedException;
    
    boolean sendDeleteRequest(String endpoint) throws IOException, DeleteRequestFailedException;
}
