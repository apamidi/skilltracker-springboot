/**
 * 
 */
package com.fsd.springboot.skillTracker.util;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * @author AP
 *
 */

@ControllerAdvice
@RestController
public class CustomRestExceptionHandler extends ResponseEntityExceptionHandler {

	
	@ExceptionHandler(SkillTrackerEntityNotFoundException.class)
	  public final ResponseEntity<ApiError> handleTaskNotFoundException(SkillTrackerEntityNotFoundException ex, WebRequest request) {
		ApiError errorDetails = new ApiError(new Date(), ex.getMessage(),
	        request.getDescription(false));
	    return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
	  }
	
	@ExceptionHandler(Exception.class)
	  public final ResponseEntity<ApiError> handleAllExceptions(Exception ex, WebRequest request) {
		ApiError errorDetails = new ApiError(new Date(), ex.getMessage(),
	        request.getDescription(false));
	    return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
	  }
}
