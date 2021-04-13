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

    final Scheduling scheduling = Scheduling.builder()
                .uuid(UUID.randomUUID())
                .send(LocalDateTime.now())
                .message("Reunião amanhã as 09h00.")
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
                            .sended(null)
                            .status(Status.builder()
                                    .id(1)
                                    .name("Agendado")
                                    .build())
                            .build())
                .schedulingSms(SchedulingSms.builder()
                            .uuid(UUID.randomUUID())
                            .sended(null)
                            .status(Status.builder()
                                    .id(1)
                                    .name("Agendado")
                                    .build())
                            .build())
                .schedulingPush(SchedulingPush.builder()
                            .uuid(UUID.randomUUID())
                            .sended(null)
                            .status(Status.builder()
                                    .id(1)
                                    .name("Agendado")
                                    .build())
                            .build())
                .schedulingWhatsapp(SchedulingWhatsapp.builder()
                            .uuid(UUID.randomUUID())
                            .sended(null)
                            .status(Status.builder()
                                    .id(1)
                                    .name("Agendado")
                                    .build())
                            .build())
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
}