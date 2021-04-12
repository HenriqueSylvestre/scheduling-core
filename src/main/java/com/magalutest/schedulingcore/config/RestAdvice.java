package com.magalutest.schedulingcore.config;

import com.magalutest.schedulingcore.exception.NotFoundException;
import com.magalutest.schedulingcore.exception.OneOrMoreParametersWereIncorrectly;
import com.magalutest.schedulingcore.exception.ReceiverNotContainValidMeansCommunication;
import com.magalutest.schedulingcore.exception.SendDateInvalid;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.orm.jpa.JpaObjectRetrievalFailureException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;

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
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public MessageError dataIntegrityViolationException(final DataIntegrityViolationException dataIntegrityViolationException) throws Exception {
        if(dataIntegrityViolationException.getMessage().contains("constraint [null]")) {
            return MessageError.builder()
                    .error("Bad request")
                    .message("One or more parameters were incorrectly specified, are mutually exclusive.")
                    .code(1002)
                    .build();
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

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public MessageError httpMessageNotReadableException(final HttpMessageNotReadableException httpMessageNotReadableException) {
        return MessageError.builder()
                .error("Bad request")
                .message("One or more parameters were incorrectly specified, are mutually exclusive.")
                .code(1002)
                .build();
    }

    @ExceptionHandler(SendDateInvalid.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public MessageError sendDateInvalid(final SendDateInvalid sendDateInvalid) {
        return MessageError.builder()
                .error("Bad request")
                .message("Send parameter is incorrectly specified. Shipping date has expired.")
                .code(1003)
                .build();
    }

    @ExceptionHandler(ReceiverNotContainValidMeansCommunication.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public MessageError receiverNotContainValidMeansCommunication(final ReceiverNotContainValidMeansCommunication receiverNotContainValidMeansCommunication) {
        return MessageError.builder()
                .error("Bad request")
                .message("Receiver does not contain valid means of communication.")
                .code(1004)
                .build();
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public MessageError methodArgumentTypeMismatchException(final MethodArgumentTypeMismatchException methodArgumentTypeMismatchException) {
        if(methodArgumentTypeMismatchException.getCause().getMessage().contains("Invalid UUID string:")) {
            return MessageError.builder()
                    .error("Bad request")
                    .message("UUID parameter is incorrectly specified.")
                    .code(1005)
                    .build();
        } else {
            return exception(null);
        }
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public MessageError methodArgumentNotValidException(final MethodArgumentNotValidException methodArgumentNotValidException) {
        final Map<String, String> errors = new HashMap<>();
        methodArgumentNotValidException.getBindingResult().getAllErrors().forEach((error) -> {
            final var fieldName = ((FieldError) error).getField();
            final var errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        return MessageError.builder()
                .error("Bad request")
                .message(MessageFormat.format("One or more parameters were incorrectly specified, are mutually exclusive. {0}", errors))
                .code(1006)
                .build();
    }
}
