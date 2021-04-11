package com.magalutest.schedulingcore.controller;

import com.magalutest.schedulingcore.controller.dto.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;

public abstract class AbstractController {
    public <T> ResponseEntity<Response> build(final HttpStatus httpStatus, final T entity) {
        return ResponseEntity.status(httpStatus).body(Response.builder()
                .data(Arrays.asList(entity))
                .build());
    }

    public <T> ResponseEntity build(final HttpStatus httpStatus) {
        return (ResponseEntity) ResponseEntity.status(httpStatus);
    }
}
