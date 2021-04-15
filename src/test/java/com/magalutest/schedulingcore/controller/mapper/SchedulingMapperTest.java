package com.magalutest.schedulingcore.controller.mapper;

import com.magalutest.schedulingcore.controller.dto.SchedulingRequestDTO;
import com.magalutest.schedulingcore.model.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;
import java.util.UUID;

@ExtendWith(SpringExtension.class)
class SchedulingMapperTest {

    @InjectMocks
    private SchedulingMapper schedulingMapper;

    final SchedulingEmail schedulingEmail = SchedulingEmail.builder()
            .uuid(UUID.randomUUID())
            .sended(null)
            .status(Status.builder()
                    .id(1)
                    .name("Agendado")
                    .build())
            .build();

    final SchedulingSms schedulingSms = SchedulingSms.builder()
            .uuid(UUID.randomUUID())
            .sended(null)
            .status(Status.builder()
                    .id(1)
                    .name("Agendado")
                    .build())
            .build();

    final SchedulingPush schedulingPush = SchedulingPush.builder()
            .uuid(UUID.randomUUID())
            .sended(null)
            .status(Status.builder()
                    .id(1)
                    .name("Agendado")
                    .build())
            .build();

    final SchedulingWhatsapp schedulingWhatsapp = SchedulingWhatsapp.builder()
            .uuid(UUID.randomUUID())
            .sended(null)
            .status(Status.builder()
                    .id(1)
                    .name("Agendado")
                    .build())
            .build();

    final Customer customer = Customer.builder()
            .uuid(UUID.randomUUID())
            .name("João da Silva")
            .email("joaosilva@gmail.com")
            .phone("5516991778431")
            .push("cP9ZoomxpYgetvIhAp6AhYECKsj1")
            .whatsapp(true)
            .build();

    final Scheduling scheduling = Scheduling.builder()
                .uuid(UUID.randomUUID())
                .send(LocalDateTime.now())
                .message("Reunião amanhã as 09h00.")
                .receiver(customer)
                .schedulingEmail(schedulingEmail)
                .schedulingSms(schedulingSms)
                .schedulingPush(schedulingPush)
                .schedulingWhatsapp(schedulingWhatsapp)
                .build();

    @Test
    void mapperDTOtoSchedulingSucess() {
        final var schedulingRequestDTO = SchedulingRequestDTO.builder()
                .send(LocalDateTime.now())
                .message("Reunião amanhã às 09h00.")
                .receiver(SchedulingRequestDTO.Receiver.builder()
                        .uuid(UUID.randomUUID())
                        .build())
                .build();

        final var scheduling = schedulingMapper.mapper(schedulingRequestDTO);

        Assertions.assertEquals(schedulingRequestDTO.getSend(), scheduling.getSend());
        Assertions.assertEquals(schedulingRequestDTO.getMessage(), scheduling.getMessage());
        Assertions.assertNotNull(schedulingRequestDTO.getReceiver());
        Assertions.assertEquals(schedulingRequestDTO.getReceiver().getUuid(), scheduling.getReceiver().getUuid());
    }

    @Test
    void mapperSchedulingToResponseDTOSucess() {
        final var schedulingResponseDTO = schedulingMapper.mapper(scheduling);

        Assertions.assertEquals(schedulingResponseDTO.getUuid(), scheduling.getUuid());
        Assertions.assertEquals(schedulingResponseDTO.getSend(), scheduling.getSend());
        Assertions.assertEquals(schedulingResponseDTO.getMessage(), scheduling.getMessage());

        Assertions.assertNotNull(schedulingResponseDTO.getReceiver());
        Assertions.assertEquals(schedulingResponseDTO.getReceiver().getUuid(), scheduling.getReceiver().getUuid());
        Assertions.assertEquals(schedulingResponseDTO.getReceiver().getName(), scheduling.getReceiver().getName());
        Assertions.assertEquals(schedulingResponseDTO.getReceiver().getEmail(), scheduling.getReceiver().getEmail());
        Assertions.assertEquals(schedulingResponseDTO.getReceiver().getPhone(), scheduling.getReceiver().getPhone());
        Assertions.assertEquals(schedulingResponseDTO.getReceiver().getPush(), scheduling.getReceiver().getPush());
        Assertions.assertEquals(schedulingResponseDTO.getReceiver().isWhatsapp(), scheduling.getReceiver().isWhatsapp());

        Assertions.assertNotNull(schedulingResponseDTO.getSchedulingEmail());
        Assertions.assertEquals(schedulingResponseDTO.getSchedulingEmail().getUuid(), scheduling.getSchedulingEmail().getUuid());
        Assertions.assertEquals(schedulingResponseDTO.getSchedulingEmail().getSended(), scheduling.getSchedulingEmail().getSended());
        Assertions.assertNotNull(schedulingResponseDTO.getSchedulingEmail().getStatus());
        Assertions.assertEquals(schedulingResponseDTO.getSchedulingEmail().getStatus().getId(), scheduling.getSchedulingEmail().getStatus().getId());
        Assertions.assertEquals(schedulingResponseDTO.getSchedulingEmail().getStatus().getName(), scheduling.getSchedulingEmail().getStatus().getName());

        Assertions.assertNotNull(schedulingResponseDTO.getSchedulingSms());
        Assertions.assertEquals(schedulingResponseDTO.getSchedulingSms().getUuid(), scheduling.getSchedulingSms().getUuid());
        Assertions.assertEquals(schedulingResponseDTO.getSchedulingSms().getSended(), scheduling.getSchedulingSms().getSended());
        Assertions.assertNotNull(schedulingResponseDTO.getSchedulingSms().getStatus());
        Assertions.assertEquals(schedulingResponseDTO.getSchedulingSms().getStatus().getId(), scheduling.getSchedulingSms().getStatus().getId());
        Assertions.assertEquals(schedulingResponseDTO.getSchedulingSms().getStatus().getName(), scheduling.getSchedulingSms().getStatus().getName());

        Assertions.assertNotNull(schedulingResponseDTO.getSchedulingPush());
        Assertions.assertEquals(schedulingResponseDTO.getSchedulingPush().getUuid(), scheduling.getSchedulingPush().getUuid());
        Assertions.assertEquals(schedulingResponseDTO.getSchedulingPush().getSended(), scheduling.getSchedulingPush().getSended());
        Assertions.assertNotNull(schedulingResponseDTO.getSchedulingPush().getStatus());
        Assertions.assertEquals(schedulingResponseDTO.getSchedulingPush().getStatus().getId(), scheduling.getSchedulingPush().getStatus().getId());
        Assertions.assertEquals(schedulingResponseDTO.getSchedulingPush().getStatus().getName(), scheduling.getSchedulingPush().getStatus().getName());

        Assertions.assertNotNull(schedulingResponseDTO.getSchedulingWhatsapp());
        Assertions.assertEquals(schedulingResponseDTO.getSchedulingWhatsapp().getUuid(), scheduling.getSchedulingWhatsapp().getUuid());
        Assertions.assertEquals(schedulingResponseDTO.getSchedulingWhatsapp().getSended(), scheduling.getSchedulingWhatsapp().getSended());
        Assertions.assertNotNull(schedulingResponseDTO.getSchedulingWhatsapp().getStatus());
        Assertions.assertEquals(schedulingResponseDTO.getSchedulingWhatsapp().getStatus().getId(), scheduling.getSchedulingWhatsapp().getStatus().getId());
        Assertions.assertEquals(schedulingResponseDTO.getSchedulingWhatsapp().getStatus().getName(), scheduling.getSchedulingWhatsapp().getStatus().getName());
    }

    @Test
    void mapperSchedulingToSchedulingTypesStatusResponseDTOSucess() {
        final var statusResponseDTO = schedulingMapper.mapperStatus(scheduling);

        Assertions.assertEquals(statusResponseDTO.getUuid(), scheduling.getUuid());

        Assertions.assertNotNull(statusResponseDTO.getSchedulingEmail());
        Assertions.assertEquals(statusResponseDTO.getSchedulingEmail().getUuid(), scheduling.getSchedulingEmail().getUuid());
        Assertions.assertEquals(statusResponseDTO.getSchedulingEmail().getSended(), scheduling.getSchedulingEmail().getSended());
        Assertions.assertNotNull(statusResponseDTO.getSchedulingEmail().getStatus());
        Assertions.assertEquals(statusResponseDTO.getSchedulingEmail().getStatus().getId(), scheduling.getSchedulingEmail().getStatus().getId());
        Assertions.assertEquals(statusResponseDTO.getSchedulingEmail().getStatus().getName(), scheduling.getSchedulingEmail().getStatus().getName());

        Assertions.assertNotNull(statusResponseDTO.getSchedulingSms());
        Assertions.assertEquals(statusResponseDTO.getSchedulingSms().getUuid(), scheduling.getSchedulingSms().getUuid());
        Assertions.assertEquals(statusResponseDTO.getSchedulingSms().getSended(), scheduling.getSchedulingSms().getSended());
        Assertions.assertNotNull(statusResponseDTO.getSchedulingSms().getStatus());
        Assertions.assertEquals(statusResponseDTO.getSchedulingSms().getStatus().getId(), scheduling.getSchedulingSms().getStatus().getId());
        Assertions.assertEquals(statusResponseDTO.getSchedulingSms().getStatus().getName(), scheduling.getSchedulingSms().getStatus().getName());

        Assertions.assertNotNull(statusResponseDTO.getSchedulingPush());
        Assertions.assertEquals(statusResponseDTO.getSchedulingPush().getUuid(), scheduling.getSchedulingPush().getUuid());
        Assertions.assertEquals(statusResponseDTO.getSchedulingPush().getSended(), scheduling.getSchedulingPush().getSended());
        Assertions.assertNotNull(statusResponseDTO.getSchedulingPush().getStatus());
        Assertions.assertEquals(statusResponseDTO.getSchedulingPush().getStatus().getId(), scheduling.getSchedulingPush().getStatus().getId());
        Assertions.assertEquals(statusResponseDTO.getSchedulingPush().getStatus().getName(), scheduling.getSchedulingPush().getStatus().getName());

        Assertions.assertNotNull(statusResponseDTO.getSchedulingWhatsapp());
        Assertions.assertEquals(statusResponseDTO.getSchedulingWhatsapp().getUuid(), scheduling.getSchedulingWhatsapp().getUuid());
        Assertions.assertEquals(statusResponseDTO.getSchedulingWhatsapp().getSended(), scheduling.getSchedulingWhatsapp().getSended());
        Assertions.assertNotNull(statusResponseDTO.getSchedulingWhatsapp().getStatus());
        Assertions.assertEquals(statusResponseDTO.getSchedulingWhatsapp().getStatus().getId(), scheduling.getSchedulingWhatsapp().getStatus().getId());
        Assertions.assertEquals(statusResponseDTO.getSchedulingWhatsapp().getStatus().getName(), scheduling.getSchedulingWhatsapp().getStatus().getName());
    }

    @Test
    void getSchedulingTypeEmailSuccess() {
        var schedulingTypeResult = schedulingMapper.getSchedulingType(schedulingEmail);

        Assertions.assertNotNull(schedulingTypeResult);
        Assertions.assertEquals(schedulingEmail.getUuid(), schedulingTypeResult.getUuid());
        Assertions.assertEquals(schedulingEmail.getSended(), schedulingTypeResult.getSended());
        Assertions.assertNotNull(schedulingTypeResult.getStatus());
        Assertions.assertEquals(schedulingEmail.getStatus().getId(), schedulingTypeResult.getStatus().getId());
        Assertions.assertEquals(schedulingEmail.getStatus().getName(), schedulingTypeResult.getStatus().getName());
    }

    @Test
    void getSchedulingTypeEmailSuccessWithStatusNull() {
        final SchedulingEmail schedulingEmail = SchedulingEmail.builder()
                .uuid(UUID.randomUUID())
                .sended(null)
                .status(null)
                .build();
        var schedulingTypeResult = schedulingMapper.getSchedulingType(schedulingEmail);

        Assertions.assertNotNull(schedulingTypeResult);
        Assertions.assertEquals(schedulingEmail.getUuid(), schedulingTypeResult.getUuid());
        Assertions.assertEquals(schedulingEmail.getSended(), schedulingTypeResult.getSended());
        Assertions.assertNull(schedulingTypeResult.getStatus());
    }

    @Test
    void getSchedulingTypeSmsSuccess() {
        var schedulingTypeResult = schedulingMapper.getSchedulingType(schedulingSms);

        Assertions.assertNotNull(schedulingTypeResult);
        Assertions.assertEquals(schedulingSms.getUuid(), schedulingTypeResult.getUuid());
        Assertions.assertEquals(schedulingSms.getSended(), schedulingTypeResult.getSended());
        Assertions.assertNotNull(schedulingTypeResult.getStatus());
        Assertions.assertEquals(schedulingSms.getStatus().getId(), schedulingTypeResult.getStatus().getId());
        Assertions.assertEquals(schedulingSms.getStatus().getName(), schedulingTypeResult.getStatus().getName());
    }

    @Test
    void getSchedulingTypeSmsSuccessWithStatusNull() {
        final SchedulingSms schedulingSms = SchedulingSms.builder()
                .uuid(UUID.randomUUID())
                .sended(null)
                .status(null)
                .build();
        var schedulingTypeResult = schedulingMapper.getSchedulingType(schedulingSms);

        Assertions.assertNotNull(schedulingTypeResult);
        Assertions.assertEquals(schedulingSms.getUuid(), schedulingTypeResult.getUuid());
        Assertions.assertEquals(schedulingSms.getSended(), schedulingTypeResult.getSended());
        Assertions.assertNull(schedulingTypeResult.getStatus());
    }

    @Test
    void getSchedulingTypePushSuccess() {
        var schedulingTypeResult = schedulingMapper.getSchedulingType(schedulingPush);

        Assertions.assertNotNull(schedulingTypeResult);
        Assertions.assertEquals(schedulingPush.getUuid(), schedulingTypeResult.getUuid());
        Assertions.assertEquals(schedulingPush.getSended(), schedulingTypeResult.getSended());
        Assertions.assertNotNull(schedulingTypeResult.getStatus());
        Assertions.assertEquals(schedulingPush.getStatus().getId(), schedulingTypeResult.getStatus().getId());
        Assertions.assertEquals(schedulingPush.getStatus().getName(), schedulingTypeResult.getStatus().getName());
    }

    @Test
    void getSchedulingTypePushSuccessWithStatusNull() {
        final SchedulingPush schedulingPush = SchedulingPush.builder()
                .uuid(UUID.randomUUID())
                .sended(null)
                .status(null)
                .build();
        var schedulingTypeResult = schedulingMapper.getSchedulingType(schedulingPush);

        Assertions.assertNotNull(schedulingTypeResult);
        Assertions.assertEquals(schedulingPush.getUuid(), schedulingTypeResult.getUuid());
        Assertions.assertEquals(schedulingPush.getSended(), schedulingTypeResult.getSended());
        Assertions.assertNull(schedulingTypeResult.getStatus());
    }

    @Test
    void getSchedulingTypeWhatsappSuccess() {
        var schedulingTypeResult = schedulingMapper.getSchedulingType(schedulingWhatsapp);

        Assertions.assertNotNull(schedulingTypeResult);
        Assertions.assertEquals(schedulingWhatsapp.getUuid(), schedulingTypeResult.getUuid());
        Assertions.assertEquals(schedulingWhatsapp.getSended(), schedulingTypeResult.getSended());
        Assertions.assertNotNull(schedulingTypeResult.getStatus());
        Assertions.assertEquals(schedulingWhatsapp.getStatus().getId(), schedulingTypeResult.getStatus().getId());
        Assertions.assertEquals(schedulingWhatsapp.getStatus().getName(), schedulingTypeResult.getStatus().getName());
    }

    @Test
    void getSchedulingTypeWhatsappSuccessWithStatusNull() {
        final SchedulingWhatsapp schedulingWhatsapp = SchedulingWhatsapp.builder()
                .uuid(UUID.randomUUID())
                .sended(null)
                .status(null)
                .build();
        var schedulingTypeResult = schedulingMapper.getSchedulingType(schedulingWhatsapp);

        Assertions.assertNotNull(schedulingTypeResult);
        Assertions.assertEquals(schedulingWhatsapp.getUuid(), schedulingTypeResult.getUuid());
        Assertions.assertEquals(schedulingWhatsapp.getSended(), schedulingTypeResult.getSended());
        Assertions.assertNull(schedulingTypeResult.getStatus());
    }

    @Test
    void getSchedulingEmailSuccess() {
        var schedulingEmailResult = schedulingMapper.getSchedulingEmail(schedulingEmail);

        Assertions.assertNotNull(schedulingEmailResult);
        Assertions.assertEquals(schedulingEmail.getUuid(), schedulingEmailResult.getUuid());
        Assertions.assertEquals(schedulingEmail.getSended(), schedulingEmailResult.getSended());
        Assertions.assertNotNull(schedulingEmailResult.getStatus());
        Assertions.assertEquals(schedulingEmail.getStatus().getId(), schedulingEmailResult.getStatus().getId());
        Assertions.assertEquals(schedulingEmail.getStatus().getName(), schedulingEmailResult.getStatus().getName());
    }

    @Test
    void getSchedulingEmailNullSuccess() {
        var schedulingEmailResult = schedulingMapper.getSchedulingEmail(null);
        Assertions.assertNull(schedulingEmailResult);
    }

    @Test
    void getSchedulingSmsSuccess() {
        var schedulingSmsResult = schedulingMapper.getSchedulingSms(schedulingSms);

        Assertions.assertNotNull(schedulingSmsResult);
        Assertions.assertEquals(schedulingSms.getUuid(), schedulingSmsResult.getUuid());
        Assertions.assertEquals(schedulingSms.getSended(), schedulingSmsResult.getSended());
        Assertions.assertNotNull(schedulingSmsResult.getStatus());
        Assertions.assertEquals(schedulingSms.getStatus().getId(), schedulingSmsResult.getStatus().getId());
        Assertions.assertEquals(schedulingSms.getStatus().getName(), schedulingSmsResult.getStatus().getName());
    }

    @Test
    void getSchedulingSmsNullSuccess() {
        var schedulingSmsResult = schedulingMapper.getSchedulingSms(null);
        Assertions.assertNull(schedulingSmsResult);
    }

    @Test
    void getSchedulingPushSuccess() {
        var schedulingPushResult = schedulingMapper.getSchedulingPush(schedulingPush);

        Assertions.assertNotNull(schedulingPushResult);
        Assertions.assertEquals(schedulingPush.getUuid(), schedulingPushResult.getUuid());
        Assertions.assertEquals(schedulingPush.getSended(), schedulingPushResult.getSended());
        Assertions.assertNotNull(schedulingPushResult.getStatus());
        Assertions.assertEquals(schedulingPush.getStatus().getId(), schedulingPushResult.getStatus().getId());
        Assertions.assertEquals(schedulingPush.getStatus().getName(), schedulingPushResult.getStatus().getName());
    }

    @Test
    void getSchedulingPushNullSuccess() {
        var schedulingPushResult = schedulingMapper.getSchedulingPush(null);
        Assertions.assertNull(schedulingPushResult);
    }

    @Test
    void getSchedulingWhatsappSuccess() {
        var schedulingWhatsappResult = schedulingMapper.getSchedulingWhatsapp(schedulingWhatsapp);

        Assertions.assertNotNull(schedulingWhatsappResult);
        Assertions.assertEquals(schedulingWhatsapp.getUuid(), schedulingWhatsappResult.getUuid());
        Assertions.assertEquals(schedulingWhatsapp.getSended(), schedulingWhatsappResult.getSended());
        Assertions.assertNotNull(schedulingWhatsappResult.getStatus());
        Assertions.assertEquals(schedulingWhatsapp.getStatus().getId(), schedulingWhatsappResult.getStatus().getId());
        Assertions.assertEquals(schedulingWhatsapp.getStatus().getName(), schedulingWhatsappResult.getStatus().getName());
    }

    @Test
    void getSchedulingWhatsappNullSuccess() {
        var schedulingWhatsappResult = schedulingMapper.getSchedulingWhatsapp(null);
        Assertions.assertNull(schedulingWhatsappResult);
    }

    @Test
    void getReceiverSuccess() {
        var receiverResult = schedulingMapper.getReceiver(customer);

        Assertions.assertNotNull(receiverResult);
        Assertions.assertEquals(customer.getUuid(), receiverResult.getUuid());
        Assertions.assertEquals(customer.getName(), receiverResult.getName());
        Assertions.assertEquals(customer.getEmail(), receiverResult.getEmail());
        Assertions.assertEquals(customer.getPhone(), receiverResult.getPhone());
        Assertions.assertEquals(customer.getPush(), receiverResult.getPush());
        Assertions.assertEquals(customer.isWhatsapp(), receiverResult.isWhatsapp());
    }

    @Test
    void getReceiverNullSuccess() {
        var receiverResult = schedulingMapper.getReceiver(null);
        Assertions.assertNull(receiverResult);
    }
}