/**
 * 
 */
package com.fsd.springboot.skillTracker.util;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

/**
 * @author AP
 *
 */

@ResponseStatus(HttpStatus.NOT_FOUND)
public class SkillTrackerEntityNotFoundException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SkillTrackerEntityNotFoundException(String exception) {
	    super(exception);
	  }

}
