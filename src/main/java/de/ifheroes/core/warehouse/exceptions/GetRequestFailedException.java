package de.ifheroes.core.warehouse.exceptions;

import org.bukkit.Bukkit;

import de.ifheroes.core.Logger;
import de.ifheroes.core.Logger.LogLevel;


/**
 * Custom exception thrown when a GET request fails.
 * This exception includes the endpoint URL and the HTTP response code that caused the failure.
 */
public class GetRequestFailedException extends Exception {

    private static final long serialVersionUID = 1L;
    private final String endPoint;
    private final int httpResponse;

    /**
     * Constructor for the GetRequestFailedException.
     * 
     * @param endPoint The URL endpoint that the GET request was sent to.
     * @param httpResponse The HTTP response code received from the failed GET request.
     */
    public GetRequestFailedException(String endPoint, int httpResponse) {
        this.endPoint = endPoint;
        this.httpResponse = httpResponse;
    }

    /**
     * Overrides the printStackTrace method to include custom logging for the failed GET request.
     * In addition to the standard stack trace, it logs a warning message with the endpoint and HTTP response code.
     */
    @Override
    public void printStackTrace() {
        super.printStackTrace();
        new Logger(LogLevel.ERROR).error("Couldn't send GET request to endpoint %s | Error code: %s".formatted(endPoint, httpResponse));
    }
    
    public int getHttpResponse() {
		return httpResponse;
	}
}
