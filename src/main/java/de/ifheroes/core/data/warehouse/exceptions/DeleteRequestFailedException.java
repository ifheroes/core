package de.ifheroes.core.data.warehouse.exceptions;

import de.ifheroes.core.Logger;

public class DeleteRequestFailedException extends Exception {
	  private static final long serialVersionUID = 1L;
	  
	  private final String endPoint;
	  
	  private final int httpResponse;
	  
	  public DeleteRequestFailedException(String endPoint, int httpResponse) {
	    this.endPoint = endPoint;
	    this.httpResponse = httpResponse;
	  }
	  
	  public void printStackTrace() {
	    super.printStackTrace();
	    new Logger(Logger.LogLevel.ERROR).error("Couldn't send DELETE request to endpoint %s | Error code: %d".formatted(endPoint, httpResponse));
	  }
	}
