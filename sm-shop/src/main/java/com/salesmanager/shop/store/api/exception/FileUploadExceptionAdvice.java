package com.salesmanager.shop.store.api.exception;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

@ControllerAdvice
public class FileUploadExceptionAdvice {
	

	private static final Logger log = LoggerFactory.getLogger(FileUploadExceptionAdvice.class);
    
    @ExceptionHandler(MaxUploadSizeExceededException.class)
    @ResponseStatus(HttpStatus.PAYLOAD_TOO_LARGE)
    public @ResponseBody ErrorEntity handleFileException(Exception exception) {
        log.error(exception.getMessage(), exception);
        ErrorEntity errorEntity = new ErrorEntity();

        String resultMessage = exception.getLocalizedMessage() != null ? exception.getLocalizedMessage() : exception.getMessage();
        Optional.ofNullable(resultMessage)
                .ifPresent(errorEntity::setMessage);
        return errorEntity;
    }

}
