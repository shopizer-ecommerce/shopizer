package com.salesmanager.shop.store.controller.error;

import com.salemanager.shop.exception.ResourceNotFoundException;
import com.salemanager.shop.exception.ServiceRuntimeException;
import com.salesmanager.shop.store.api.exception.RestApiException;
import com.salesmanager.shop.store.controller.error.model.ErrorEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.Optional;

@Slf4j
@ControllerAdvice
public class RestErrorHandler {

    /**
     * Generic exception serviceException handler
     */
    @ExceptionHandler(ServiceRuntimeException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @Produces({MediaType.APPLICATION_JSON})
    public ErrorEntity handleServiceException(ServiceRuntimeException exception) {
        log.error(exception.getMessage(), exception);
        ErrorEntity errorEntity = createErrorEntity(exception.getErrorCode(), exception.getMessage());
        return errorEntity;
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @Produces({MediaType.APPLICATION_JSON})
    public ErrorEntity handleServiceException(ResourceNotFoundException exception) {
        log.error(exception.getMessage(), exception);

        ErrorEntity errorEntity = createErrorEntity(exception.getErrorCode(), exception.getMessage());
        return errorEntity;
    }

    @ExceptionHandler(RestApiException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @Produces({MediaType.APPLICATION_JSON})
    public ErrorEntity handleRestApiException(RestApiException exception) {
        log.error(exception.getMessage(), exception);

        ErrorEntity errorEntity = createErrorEntity(exception.getErrorCode(), exception.getMessage());
        return errorEntity;
    }

    private ErrorEntity createErrorEntity(String errorCode, String message) {
        ErrorEntity errorEntity = new ErrorEntity();
        Optional.ofNullable(errorCode)
                .ifPresent(errorEntity::setErrorCode);

        Optional.ofNullable(message)
                .ifPresent(errorEntity::setMessage);
        return errorEntity;
    }
}
