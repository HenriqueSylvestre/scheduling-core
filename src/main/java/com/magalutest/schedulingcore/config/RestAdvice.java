package com.magalutest.schedulingcore.config;

import com.magalutest.schedulingcore.exception.NotFoundException;
import com.magalutest.schedulingcore.exception.OneOrMoreParametersWereIncorrectly;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.orm.jpa.JpaObjectRetrievalFailureException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class RestAdvice {

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public MessageError exception(final Exception exception) {
        return MessageError.builder()
                .error("Internal server error")
                .message("Was encountered an error when processing your request. We apologize for the inconvenience.")
                .code(1000)
                .build();
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public MessageError notFoundException(final NotFoundException notFoundException) {
        return MessageError.builder()
                .error("Register not found")
                .message("The requested resource could not be found.")
                .code(1001)
                .build();
    }

    @ExceptionHandler(EmptyResultDataAccessException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public MessageError emptyResultDataAccessException(final EmptyResultDataAccessException emptyResultDataAccessException) {
        return MessageError.builder()
                .error("Register not found")
                .message("The requested resource could not be found.")
                .code(1001)
                .build();
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public MessageError dataIntegrityViolationException(final DataIntegrityViolationException dataIntegrityViolationException) throws Exception {
        if(dataIntegrityViolationException.getMessage().contains("constraint [null]")) {
            log.info(dataIntegrityViolationException.getMessage());
            throw new OneOrMoreParametersWereIncorrectly();
        }
        throw new Exception();
    }

    @ExceptionHandler(OneOrMoreParametersWereIncorrectly.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public MessageError oneOrMoreParametersWereIncorrectly(final OneOrMoreParametersWereIncorrectly oneOrMoreParametersWereIncorrectly) {
        return MessageError.builder()
                .error("Bad request")
                .message("One or more parameters were incorrectly specified, are mutually exclusive.")
                .code(1002)
                .build();
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public MessageError constraintViolationException(final ConstraintViolationException constraintViolationException) {
        return MessageError.builder()
                .error("Bad request")
                .message("One or more parameters were incorrectly specified, are mutually exclusive.")
                .code(1002)
                .build();
    }

    @ExceptionHandler(JpaObjectRetrievalFailureException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public MessageError jpaObjectRetrievalFailureException(final JpaObjectRetrievalFailureException jpaObjectRetrievalFailureException) {
        return MessageError.builder()
                .error("Bad request")
                .message("One or more parameters were incorrectly specified, are mutually exclusive.")
                .code(1002)
                .build();
    }
}
