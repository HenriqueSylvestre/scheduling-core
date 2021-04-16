package com.magalutest.schedulingcore.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.magalutest.schedulingcore.config.MessageError;
import com.magalutest.schedulingcore.controller.dto.CustomerRequestDTO;
import com.magalutest.schedulingcore.controller.dto.CustomerResponseDTO;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static io.restassured.RestAssured.given;

class CustomerControllerTest {

    private static final String URL = "http://localhost:8080/customers";
    final String phone = "5516981778430";
    final CustomerRequestDTO customerRequestDTO = CustomerRequestDTO.builder()
            .name("Jose Marques da Silveira")
            .email("josemsilveira123@gmail.com")
            .phone(phone)
            .push("c0cb5452-a827-4458-bee9-8852715b6795")
            .whatsapp(false)
            .build();

    CustomerResponseDTO customerResponseDTO;

    private CustomerResponseDTO createCustomer() {
        final var responseDTO = given().contentType("application/json").body(customerRequestDTO)
                .when().post(URL)
                .then().statusCode(201).extract().jsonPath().getList("data", CustomerResponseDTO.class);
        return responseDTO.get(0);
    }

    private void deleteCustomer(UUID uuid) {
        given().contentType("application/json").pathParam("uuid", uuid)
                .when().delete(URL+"/{uuid}")
                .then().statusCode(204);
    }

    @Test
    void create() throws JsonProcessingException {
        final var responseDTO = given().contentType("application/json").body(customerRequestDTO)
                .when().post(URL)
                .then().statusCode(201).extract().jsonPath().getList("data", CustomerResponseDTO.class);

        Assertions.assertNotNull(responseDTO);
        Assertions.assertEquals(1, responseDTO.size());
        Assertions.assertNotNull(responseDTO.get(0).getUuid());
        Assertions.assertEquals(customerRequestDTO.getName(), responseDTO.get(0).getName());
        Assertions.assertEquals(customerRequestDTO.getEmail(), responseDTO.get(0).getEmail());
        Assertions.assertEquals(customerRequestDTO.getPhone(), responseDTO.get(0).getPhone());
        Assertions.assertEquals(customerRequestDTO.getPush(), responseDTO.get(0).getPush());
        Assertions.assertEquals(customerRequestDTO.isWhatsapp(), responseDTO.get(0).isWhatsapp());

        deleteCustomer(responseDTO.get(0).getUuid());
    }

    @Test
    void findByPhoneSuccess() {
        final var customerResponseDTO = createCustomer();
        final var responseDTO = given().contentType("application/json").queryParam("phone", customerResponseDTO.getPhone())
                .when().get(URL)
                .then().statusCode(200).extract().jsonPath().getList("data", CustomerResponseDTO.class);

        Assertions.assertNotNull(responseDTO);
        Assertions.assertEquals(1, responseDTO.size());
        Assertions.assertEquals(customerResponseDTO.getUuid(), responseDTO.get(0).getUuid());
        Assertions.assertEquals(customerResponseDTO.getPhone(), responseDTO.get(0).getPhone());

        deleteCustomer(responseDTO.get(0).getUuid());
    }

    @Test
    void findByPhoneNotFound() {
        final var messageError = given().contentType("application/json").queryParam("phone", "5599999999999")
                .when().get(URL)
                .then().statusCode(404).extract().as(MessageError.class);

        Assertions.assertNotNull(messageError);
        Assertions.assertEquals("Register not found", messageError.getError());
        Assertions.assertEquals("The requested resource could not be found.", messageError.getMessage());
    }

    @Test
    void findSuccess() {
        final var customerResponseDTO = createCustomer();
        final var responseDTO = given().contentType("application/json").pathParam("uuid", customerResponseDTO.getUuid())
                .when().get(URL + "/{uuid}")
                .then().statusCode(200).extract().jsonPath().getList("data", CustomerResponseDTO.class);

        Assertions.assertNotNull(responseDTO);
        Assertions.assertEquals(1, responseDTO.size());
        Assertions.assertEquals(customerResponseDTO.getUuid(), responseDTO.get(0).getUuid());

        deleteCustomer(responseDTO.get(0).getUuid());
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
    void delete() {
        final var customerResponseDTO = createCustomer();
        given().contentType("application/json").pathParam("uuid", customerResponseDTO.getUuid())
                .when().delete(URL+"/{uuid}")
                .then().statusCode(204);
    }
}