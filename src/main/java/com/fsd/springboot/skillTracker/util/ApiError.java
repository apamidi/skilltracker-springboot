/**
 * 
 */
package com.fsd.springboot.skillTracker.util;

import java.util.Arrays;
import java.util.Date;
import java.util.List;


/**
 * @author AP
 *
 */
public class ApiError {

	private Date timestamp;
	  private String message;
	  private String details;

	  public ApiError(Date timestamp, String message, String details) {
	    super();
	    this.timestamp = timestamp;
	    this.message = message;
	    this.details = details;
	  }

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	  
    
}
