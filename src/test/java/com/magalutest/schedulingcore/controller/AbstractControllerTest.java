package com.magalutest.schedulingcore.controller;

import com.magalutest.schedulingcore.model.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.UUID;

class AbstractControllerTest {

    Scheduling scheduling = Scheduling.builder()
            .uuid(UUID.randomUUID())
            .send(LocalDateTime.now())
            .message("Reunião amanhã às 09h00.")
            .receiver(Customer.builder()
                        .uuid(UUID.randomUUID())
                        .name("João da Silva")
                        .email("joaosilva@gmail.com")
                        .phone("5516991778431")
                        .push("cP9ZoomxpYgetvIhAp6AhYECKsj1")
                        .whatsapp(true)
                        .build())
            .schedulingEmail(SchedulingEmail.builder()
                        .uuid(UUID.randomUUID())
                        .sended(LocalDateTime.now())
                        .status(Status.builder()
                                .id(1)
                                .name("Agendado")
                                .build())
                        .build())
            .schedulingSms(SchedulingSms.builder()
                        .uuid(UUID.randomUUID())
                        .sended(LocalDateTime.now())
                        .status(Status.builder()
                                .id(1)
                                .name("Agendado")
                                .build())
                        .build())
            .schedulingPush(SchedulingPush.builder()
                        .uuid(UUID.randomUUID())
                        .sended(LocalDateTime.now())
                        .status(Status.builder()
                                .id(1)
                                .name("Agendado")
                                .build())
                        .build())
            .schedulingWhatsapp(SchedulingWhatsapp.builder()
                        .uuid(UUID.randomUUID())
                        .sended(LocalDateTime.now())
                        .status(Status.builder()
                                .id(1)
                                .name("Agendado")
                                .build())
                        .build())
            .build();

    @Test
    void build() {
        AbstractController abstractController = Mockito.mock(AbstractController.class, Mockito.CALLS_REAL_METHODS);

        final HttpStatus status = HttpStatus.OK;

        final var responseEntity = abstractController.build(status, scheduling);

        Assertions.assertEquals(Arrays.asList(scheduling), responseEntity.getBody().getData());
        Assertions.assertEquals(status, responseEntity.getStatusCode());
    }

    @Test
    void buildOnlyStatus() {
        AbstractController abstractController = Mockito.mock(AbstractController.class, Mockito.CALLS_REAL_METHODS);

        final HttpStatus status = HttpStatus.NO_CONTENT;
        final var responseEntity = abstractController.build(status);
        Assertions.assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());
    }
}