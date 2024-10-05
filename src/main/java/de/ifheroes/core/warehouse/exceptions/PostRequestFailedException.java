package de.ifheroes.core.warehouse.exceptions;

import org.bukkit.Bukkit;


/**
 * Represents an exception that occurs when a POST request to a REST API fails.
 * This exception includes details such as the endpoint URL, the JSON input string, and the HTTP response code.
 */
public class PostRequestFailedException extends Exception {
    private static final long serialVersionUID = 1L;

    private final String endPoint;
    private final String jsonInputString;
    private final int httpResponse;

    /**
     * Constructor for the PostRequestFailedException.
     * 
     * @param endPoint The URL endpoint that the POST request was sent to.
     * @param jsonInputString The JSON input string that was sent with the POST request.
     * @param httpResponse The HTTP response code received from the failed POST request.
     */
    public PostRequestFailedException(String endPoint, String jsonInputString, int httpResponse) {
        this.endPoint = endPoint;
        this.jsonInputString = jsonInputString;
        this.httpResponse = httpResponse;
    }

    /**
     * Overrides the printStackTrace method to include custom logging for the failed POST request.
     * In addition to the standard stack trace, it logs a warning message with the endpoint, JSON input, and HTTP response code.
     */
    @Override
    public void printStackTrace() {
        super.printStackTrace();
        Bukkit.getLogger().log(java.util.logging.Level.WARNING, 
            "Couldn't send POST request to endpoint %s with JSON input %s | Error code: %d".formatted(endPoint, jsonInputString, httpResponse));
    }
}

