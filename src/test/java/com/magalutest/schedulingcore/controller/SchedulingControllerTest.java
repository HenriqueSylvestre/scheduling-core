package com.magalutest.schedulingcore.controller;

import com.magalutest.schedulingcore.SchedulingCoreApplication;
import com.magalutest.schedulingcore.config.MessageError;
import com.magalutest.schedulingcore.controller.dto.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;
import java.util.UUID;

import static io.restassured.RestAssured.given;

@SpringBootTest(classes = {SchedulingCoreApplication.class}, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@TestPropertySource(properties = {"spring.config.location=classpath:application.yml"})
@ExtendWith(SpringExtension.class)
class SchedulingControllerTest {

    private static final String URL = "/schedules";
    private static final String URL_CUSTOMERS = "/customers";
    private UUID uuidReceiver;

    private UUID createScheduling() {
        final var responseDTO = given().contentType("application/json").body(createSchedulingRequestDTO())
                .when().post(URL)
                .then().statusCode(201).extract().jsonPath().getList("data", SchedulingResponseDTO.class);
        return responseDTO.get(0).getUuid();
    }

    private void deleteScheduling(UUID uuid) {
        given().contentType("application/json").pathParam("uuid", uuid)
                .when().delete(URL+"/{uuid}")
                .then().statusCode(204);
    }

    private SchedulingRequestDTO createSchedulingRequestDTO() {
        var customerResponseDTO = createCustomer();
        return SchedulingRequestDTO.builder()
                .send(LocalDateTime.now().plusMonths(1))
                .message("Reuniao daqui um mes as 09h00")
                .receiver(SchedulingRequestDTO.Receiver.builder()
                        .uuid(customerResponseDTO.getUuid())
                        .build())
                .build();
    }

    private CustomerResponseDTO createCustomer() {
        final var responseDTO = given().contentType("application/json").body(CustomerRequestDTO.builder()
                    .name("Jose Marques da Silveira")
                    .email("josemsilveira123@gmail.com")
                    .phone("9999981778430")
                    .push("c8c95452-a827-4458-bee9-8852715b6795")
                    .whatsapp(false)
                    .build())
                .when().post(URL_CUSTOMERS)
                .then().statusCode(201).extract().jsonPath().getList("data", CustomerResponseDTO.class);
        uuidReceiver = responseDTO.get(0).getUuid();
        return responseDTO.get(0);
    }

    private void deleteCustomer(UUID uuid) {
        given().contentType("application/json").pathParam("uuid", uuid)
                .when().delete(URL_CUSTOMERS + "/{uuid}")
                .then().statusCode(204);
    }

    @Test
    void create() {
        final var responseDTO = given().contentType("application/json").body(createSchedulingRequestDTO())
            .when().post(URL)
            .then().statusCode(201).extract().jsonPath().getList("data", SchedulingResponseDTO.class);

        Assertions.assertNotNull(responseDTO);
        Assertions.assertEquals(1, responseDTO.size());
        Assertions.assertNotNull(responseDTO.get(0).getUuid());
        Assertions.assertEquals(uuidReceiver, responseDTO.get(0).getReceiver().getUuid());

        deleteScheduling(responseDTO.get(0).getUuid());
        deleteCustomer(uuidReceiver);
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
        deleteCustomer(uuidReceiver);
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
    void findStatus() {
        final var schedulingUuidCreated = createScheduling();
        final var responseDTO = given().contentType("application/json").pathParam("uuid", schedulingUuidCreated)
                .when().get(URL + "/{uuid}/status")
                .then().statusCode(200).extract().jsonPath().getList("data", SchedulingTypesStatusResponseDTO.class);

        Assertions.assertNotNull(responseDTO);
        Assertions.assertEquals(1, responseDTO.size());
        Assertions.assertEquals(schedulingUuidCreated, responseDTO.get(0).getUuid());

        deleteScheduling(responseDTO.get(0).getUuid());
        deleteCustomer(uuidReceiver);
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
    void deleteSuccess() {
        final var schedulingUuidCreated = createScheduling();
        given().contentType("application/json").pathParam("uuid", schedulingUuidCreated)
                .when().delete(URL+"/{uuid}")
                .then().statusCode(204);
        deleteCustomer(uuidReceiver);
    }
}