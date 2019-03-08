package com.salesmanager.admin.controller.exception;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.ResponseErrorHandler;

public class RestTemplateException implements ResponseErrorHandler {

	@Override
	public boolean hasError(ClientHttpResponse httpResponse) throws IOException {
		return (
		          httpResponse.getStatusCode().series() == HttpStatus.NOT_FOUND.series() 
		          || httpResponse.getStatusCode().series() == HttpStatus.Series.SERVER_ERROR);
	}

	@Override
	public void handleError(ClientHttpResponse httpResponse) throws IOException {
        if (httpResponse.getStatusCode()
                .series() == HttpStatus.Series.SERVER_ERROR) {
        				throw new AdminException("Server error");
              } else if (httpResponse.getStatusCode()
                .series() == HttpStatus.Series.CLIENT_ERROR) {
            	  //throw new AdminException("Client error");
                  if (httpResponse.getStatusCode() == HttpStatus.NOT_FOUND) {
                      throw new AdminException("Content not found " + HttpStatus.NOT_FOUND.name());
                  }
              }

	}

}
