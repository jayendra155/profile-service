package com.user.profile.api.controller;

import javax.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import com.user.profile.core.exceptions.MongoDocumentNotFoundException;
import brave.Tracer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;


/**
 * @author jayensingh
 * <p>
 * <p>
 * 27/04/22
 */
@Slf4j
@RestControllerAdvice
public class APIErrorHandler {

    private static final String INTERNAL_SERVER_ERROR_MSG = "Something went wrong";

    @Autowired
    private Tracer tracer;

    @ExceptionHandler(value = { EntityNotFoundException.class, MongoDocumentNotFoundException.class })
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void noContentFound(Throwable throwable) {
        log.info("{}. Returning 204 status. ", throwable.getMessage());
    }



    private FailureResponse getCurrentFailureMessage(String message) {
        String traceId = getCurrentTraceId();
        return new FailureResponse(message, traceId);
    }

    private String getCurrentTraceId() {
        return tracer.currentSpan().context().traceIdString();
    }

    @Data
    @AllArgsConstructor
    static class FailureResponse {

        private String message;
        private String traceId;

    }
}
