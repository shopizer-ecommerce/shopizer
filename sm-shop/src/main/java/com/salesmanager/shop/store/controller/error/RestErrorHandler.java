package com.salesmanager.shop.store.controller.error;

import com.salemanager.shop.exception.ResourceNotFoundException;
import com.salemanager.shop.exception.ServiceRuntimeException;
import com.salesmanager.shop.store.api.exception.RestApiException;
import com.salesmanager.shop.store.controller.error.model.ErrorEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.Optional;

@Slf4j
@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
@ResponseBody
public class RestErrorHandler {

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @Produces({MediaType.APPLICATION_JSON})
    public ErrorEntity handleServiceException(Exception exception) {
        log.error(exception.getMessage(), exception);
        ErrorEntity errorEntity = createErrorEntity(null, exception.getMessage(),
                exception.getLocalizedMessage());
        return errorEntity;
    }

    /**
     * Generic exception serviceException handler
     */
    @ExceptionHandler(ServiceRuntimeException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @Produces({MediaType.APPLICATION_JSON})
    public ErrorEntity handleServiceException(ServiceRuntimeException exception) {
        log.error(exception.getErrorMessage(), exception);
        ErrorEntity errorEntity = createErrorEntity(exception.getErrorCode(), exception.getErrorMessage(),
                exception.getLocalizedMessage());
        return errorEntity;
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @Produces({MediaType.APPLICATION_JSON})
    public ErrorEntity handleServiceException(ResourceNotFoundException exception) {
        log.error(exception.getErrorMessage(), exception);

        ErrorEntity errorEntity = createErrorEntity(exception.getErrorCode(), exception.getErrorMessage(),
                exception.getLocalizedMessage());
        return errorEntity;
    }

    @ExceptionHandler(RestApiException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @Produces({MediaType.APPLICATION_JSON})
    public ErrorEntity handleRestApiException(RestApiException exception) {
        log.error(exception.getErrorMessage(), exception);

        ErrorEntity errorEntity = createErrorEntity(exception.getErrorCode(), exception.getErrorMessage(),
                exception.getLocalizedMessage());
        return errorEntity;
    }

    private ErrorEntity createErrorEntity(String errorCode, String message, String detailMessage) {
        ErrorEntity errorEntity = new ErrorEntity();
        Optional.ofNullable(errorCode)
                .ifPresent(errorEntity::setErrorCode);

        String resultMessage = message != null ? message : detailMessage;
        Optional.ofNullable(resultMessage)
                .ifPresent(errorEntity::setMessage);
        return errorEntity;
    }
}
