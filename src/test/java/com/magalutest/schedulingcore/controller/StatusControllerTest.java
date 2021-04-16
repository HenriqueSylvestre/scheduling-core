package com.magalutest.schedulingcore.controller;

import com.magalutest.schedulingcore.config.MessageError;
import com.magalutest.schedulingcore.controller.dto.StatusRequestDTO;
import com.magalutest.schedulingcore.controller.dto.StatusResponseDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

class StatusControllerTest {

    private static final String URL = "http://localhost:8080/status";
    final StatusRequestDTO statusRequestDTO = StatusRequestDTO.builder()
            .name("Teste")
            .build();

    private StatusResponseDTO createStatus() {
        final var responseDTO = given().contentType("application/json").body(statusRequestDTO)
                .when().post(URL)
                .then().statusCode(201).extract().jsonPath().getList("data", StatusResponseDTO.class);
        return responseDTO.get(0);
    }

    private void deleteStatus(long id) {
        given().contentType("application/json").pathParam("id", id)
                .when().delete(URL+"/{id}")
                .then().statusCode(204);
    }

    @Test
    void createSuccess() {
        final var responseDTO = given().contentType("application/json").body(statusRequestDTO)
                .when().post(URL)
                .then().statusCode(201).extract().jsonPath().getList("data", StatusResponseDTO.class);

        Assertions.assertNotNull(responseDTO);
        Assertions.assertEquals(1, responseDTO.size());
        Assertions.assertTrue(responseDTO.get(0).getId() > 0);
        Assertions.assertEquals(statusRequestDTO.getName(), responseDTO.get(0).getName());

        deleteStatus(responseDTO.get(0).getId());
    }

    @Test
    void findSuccess() {
        final var statusResponseDTO = createStatus();
        final var responseDTO = given().contentType("application/json").pathParam("id", statusResponseDTO.getId())
                .when().get(URL + "/{id}")
                .then().statusCode(200).extract().jsonPath().getList("data", StatusResponseDTO.class);

        Assertions.assertNotNull(responseDTO);
        Assertions.assertEquals(1, responseDTO.size());
        Assertions.assertEquals(statusResponseDTO.getId(), responseDTO.get(0).getId());
        Assertions.assertEquals(statusResponseDTO.getName(), responseDTO.get(0).getName());

        deleteStatus(responseDTO.get(0).getId());
    }

    @Test
    void findNotFound() {
        final var messageError = given().contentType("application/json").pathParam("id", 5555)
                .when().get(URL + "/{id}")
                .then().statusCode(404).extract().as(MessageError.class);

        Assertions.assertNotNull(messageError);
        Assertions.assertEquals("Register not found", messageError.getError());
        Assertions.assertEquals("The requested resource could not be found.", messageError.getMessage());
    }

    @Test
    void deleteSuccess() {
        final var statusResponseDTO = createStatus();
        given().contentType("application/json").pathParam("id", statusResponseDTO.getId())
                .when().delete(URL+"/{id}")
                .then().statusCode(204);
    }
}