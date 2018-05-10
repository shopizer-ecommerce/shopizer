package com.salesmanager.admin.controller.exception;

import java.util.Date;

/** 
 * Global errors for admin
 * @author c.samson
 *
 */
public class ErrorDetails {
	
	  private Date timestamp;
	  private String message;
	  private String details;
	  private String code;
	  private String field;

	  public ErrorDetails(Date timestamp, String message, String details, String code, String field) {
	    super();
	    this.timestamp = timestamp;
	    this.message = message;
	    this.details = details;
	    this.code = code;
	    this.field = field;
	  }

}
