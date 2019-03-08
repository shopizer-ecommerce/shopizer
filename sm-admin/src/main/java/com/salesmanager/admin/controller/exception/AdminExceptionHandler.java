package com.salesmanager.admin.controller.exception;

import java.util.Date;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class AdminExceptionHandler extends ResponseEntityExceptionHandler {


	
	  @ExceptionHandler(AdminException.class)
	  protected ResponseEntity<Object> handleException (
			  AdminException ex, 
			  HttpHeaders headers, 
			  HttpStatus status, 
			  WebRequest request) {
		String message = ex.getMessage();
		String code = ex.getCode();
		String field = ex.getField();
	    ErrorDetails errorDetails = new ErrorDetails(new Date(), message,
	        message,code,field);
	    return new ResponseEntity(errorDetails, HttpStatus.BAD_REQUEST);
	  }


  
}
