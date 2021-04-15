package com.magalutest.schedulingcore.config;

import com.magalutest.schedulingcore.exception.NotFoundException;
import com.magalutest.schedulingcore.exception.OneOrMoreParametersWereIncorrectly;
import com.magalutest.schedulingcore.exception.ReceiverNotContainValidMeansCommunication;
import com.magalutest.schedulingcore.exception.SendDateInvalid;
import lombok.SneakyThrows;
import org.hibernate.exception.ConstraintViolationException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.orm.jpa.JpaObjectRetrievalFailureException;

import javax.persistence.EntityNotFoundException;
import java.sql.SQLException;

import static org.mockito.Mockito.when;

class RestAdviceTest {

    private RestAdvice restAdvice = new RestAdvice();

    @InjectMocks
    private HttpInputMessage httpInputMessage;

    @Test
    void exception() {
        var messageError = restAdvice.exception(new Exception());
        Assertions.assertEquals("Internal server error", messageError.getError());
        Assertions.assertEquals("Was encountered an error when processing your request. We apologize for the inconvenience.", messageError.getMessage());
    }

    @Test
    void notFoundException() {
        var messageError = restAdvice.notFoundException(new NotFoundException());
        Assertions.assertEquals("Register not found", messageError.getError());
        Assertions.assertEquals("The requested resource could not be found.", messageError.getMessage());
    }

    @Test
    void emptyResultDataAccessException() {
        var messageError = restAdvice.emptyResultDataAccessException(new EmptyResultDataAccessException(0));
        Assertions.assertEquals("Register not found", messageError.getError());
        Assertions.assertEquals("The requested resource could not be found.", messageError.getMessage());
    }

    @Test
    @SneakyThrows
    void dataIntegrityViolationException() {
        var messageError = restAdvice.dataIntegrityViolationException(new DataIntegrityViolationException("constraint [null]"));

        Assertions.assertEquals("Bad request", messageError.getError());
        Assertions.assertEquals("One or more parameters were incorrectly specified, are mutually exclusive.", messageError.getMessage());
    }

    @Test
    void dataIntegrityViolationExceptionThrowException() {
        Assertions.assertThrows(Exception.class, () -> {
            when(restAdvice.dataIntegrityViolationException(new DataIntegrityViolationException(""))).thenThrow(Exception.class);
            restAdvice.dataIntegrityViolationException(new DataIntegrityViolationException(""));
        });
    }

    @Test
    void oneOrMoreParametersWereIncorrectly() {
        var messageError = restAdvice.oneOrMoreParametersWereIncorrectly(new OneOrMoreParametersWereIncorrectly());
        Assertions.assertEquals("Bad request", messageError.getError());
        Assertions.assertEquals("One or more parameters were incorrectly specified, are mutually exclusive.", messageError.getMessage());
    }

    @Test
    void constraintViolationException() {
        var messageError = restAdvice.constraintViolationException(new ConstraintViolationException("", new SQLException(), ""));
        Assertions.assertEquals("Bad request", messageError.getError());
        Assertions.assertEquals("One or more parameters were incorrectly specified, are mutually exclusive.", messageError.getMessage());
    }

    @Test
    void jpaObjectRetrievalFailureException() {
        var messageError = restAdvice.jpaObjectRetrievalFailureException(new JpaObjectRetrievalFailureException(new EntityNotFoundException()));
        Assertions.assertEquals("Bad request", messageError.getError());
        Assertions.assertEquals("One or more parameters were incorrectly specified, are mutually exclusive.", messageError.getMessage());
    }

    @Test
    void httpMessageNotReadableException() {
        var messageError = restAdvice.httpMessageNotReadableException(new HttpMessageNotReadableException("", httpInputMessage));
        Assertions.assertEquals("Bad request", messageError.getError());
        Assertions.assertEquals("One or more parameters were incorrectly specified, are mutually exclusive.", messageError.getMessage());
    }

    @Test
    void sendDateInvalid() {
        var messageError = restAdvice.sendDateInvalid(new SendDateInvalid());
        Assertions.assertEquals("Bad request", messageError.getError());
        Assertions.assertEquals("Send parameter is incorrectly specified. Shipping date has expired.", messageError.getMessage());
    }

    @Test
    void receiverNotContainValidMeansCommunication() {
        var messageError = restAdvice.receiverNotContainValidMeansCommunication(new ReceiverNotContainValidMeansCommunication());
        Assertions.assertEquals("Bad request", messageError.getError());
        Assertions.assertEquals("Receiver does not contain valid means of communication.", messageError.getMessage());
    }

    @Test
    void methodArgumentTypeMismatchException() {
        var messageError = restAdvice.methodArgumentTypeMismatchException();
        Assertions.assertEquals("Bad request", messageError.getError());
        Assertions.assertEquals("One or more parameters were incorrectly specified, are mutually exclusive.", messageError.getMessage());
    }

    @Test
    void methodArgumentNotValidException() {
        var messageError = restAdvice.methodArgumentNotValidException();
        Assertions.assertEquals("Bad request", messageError.getError());
        Assertions.assertEquals("One or more parameters were incorrectly specified, are mutually exclusive.", messageError.getMessage());
    }
}