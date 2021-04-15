package com.magalutest.schedulingcore.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.magalutest.schedulingcore.config.MessageError;
import com.magalutest.schedulingcore.controller.dto.SchedulingRequestDTO;
import com.magalutest.schedulingcore.controller.dto.SchedulingResponseDTO;
import com.magalutest.schedulingcore.controller.dto.SchedulingTypesStatusResponseDTO;
import org.junit.jupiter.api.*;
import org.springframework.test.annotation.Rollback;

import java.time.LocalDateTime;
import java.util.UUID;

import static io.restassured.RestAssured.given;

class SchedulingControllerTest {

    private static final String URL = "http://localhost:8080/schedules";
    private final UUID uuidReceiver = UUID.fromString("7c66f8b7-7de1-47d4-a569-46293b730ab8");
    private final SchedulingRequestDTO schedulingRequestDTO = SchedulingRequestDTO.builder()
            .send(LocalDateTime.now().plusMonths(1))
            .message("Reuniao daqui um mes as 09h00")
            .receiver(SchedulingRequestDTO.Receiver.builder()
                    .uuid(uuidReceiver)
                    .build())
            .build();

    private UUID createScheduling() {
        final var responseDTO = given().contentType("application/json").body(schedulingRequestDTO)
                .when().post(URL)
                .then().statusCode(201).extract().jsonPath().getList("data", SchedulingResponseDTO.class);
        return responseDTO.get(0).getUuid();
    }

    private void deleteScheduling(UUID uuid) {
        given().contentType("application/json").pathParam("uuid", uuid)
                .when().delete(URL+"/{uuid}")
                .then().statusCode(204);
    }

    @Test
    void create() throws JsonProcessingException {
        final var responseDTO = given().contentType("application/json").body(schedulingRequestDTO)
            .when().post(URL)
            .then().statusCode(201).extract().jsonPath().getList("data", SchedulingResponseDTO.class);

        Assertions.assertNotNull(responseDTO);
        Assertions.assertEquals(1, responseDTO.size());
        Assertions.assertNotNull(responseDTO.get(0).getUuid());
        Assertions.assertEquals(uuidReceiver, responseDTO.get(0).getReceiver().getUuid());

        deleteScheduling(responseDTO.get(0).getUuid());
    }

    @Test
    void find() {
        final var schedulingUuidCreated = createScheduling();
        final var responseDTO = given().contentType("application/json").pathParam("uuid", schedulingUuidCreated)
                .when().get(URL + "/{uuid}")
                .then().statusCode(200).extract().jsonPath().getList("data", SchedulingResponseDTO.class);

        Assertions.assertNotNull(responseDTO);
        Assertions.assertEquals(1, responseDTO.size());
        Assertions.assertEquals(schedulingUuidCreated, responseDTO.get(0).getUuid());

        deleteScheduling(responseDTO.get(0).getUuid());
    }

    @Test
    void findNotFound() {
        final var messageError = given().contentType("application/json").pathParam("uuid", UUID.randomUUID())
                .when().get(URL + "/{uuid}")
                .then().statusCode(404).extract().as(MessageError.class);

        Assertions.assertNotNull(messageError);
        Assertions.assertEquals("Register not found", messageError.getError());
        Assertions.assertEquals("The requested resource could not be found.", messageError.getMessage());
    }

    @Test
    @Rollback
    void findStatus() {
        final var schedulingUuidCreated = createScheduling();
        final var responseDTO = given().contentType("application/json").pathParam("uuid", schedulingUuidCreated)
                .when().get(URL + "/{uuid}/status")
                .then().statusCode(200).extract().jsonPath().getList("data", SchedulingTypesStatusResponseDTO.class);

        Assertions.assertNotNull(responseDTO);
        Assertions.assertEquals(1, responseDTO.size());
        Assertions.assertEquals(schedulingUuidCreated, responseDTO.get(0).getUuid());

        deleteScheduling(responseDTO.get(0).getUuid());
    }

    @Test
    void findStatusNotFound() {
        final var messageError = given().contentType("application/json").pathParam("uuid", UUID.randomUUID())
                .when().get(URL + "/{uuid}/status")
                .then().statusCode(404).extract().as(MessageError.class);

        Assertions.assertNotNull(messageError);
        Assertions.assertEquals("Register not found", messageError.getError());
        Assertions.assertEquals("The requested resource could not be found.", messageError.getMessage());
    }

    @Test
    @Rollback
    void deleteSuccess() {
        final var schedulingUuidCreated = createScheduling();
        given().contentType("application/json").pathParam("uuid", schedulingUuidCreated)
                .when().delete(URL+"/{uuid}")
                .then().statusCode(204);
    }
}