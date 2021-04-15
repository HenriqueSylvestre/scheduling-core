package com.magalutest.schedulingcore.controller.mapper;

import com.magalutest.schedulingcore.controller.dto.SchedulingRequestDTO;
import com.magalutest.schedulingcore.controller.dto.SchedulingResponseDTO;
import com.magalutest.schedulingcore.controller.dto.SchedulingTypesStatusResponseDTO;
import com.magalutest.schedulingcore.model.*;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SchedulingMapper {

    public Scheduling mapper(final SchedulingRequestDTO requestDTO) {
        final var uuid = Optional.ofNullable(requestDTO)
                .map(r -> r.getReceiver())
                .map(receiver -> receiver.getUuid())
                .orElse(null);

        return Scheduling.builder()
                .send(requestDTO.getSend())
                .receiver(Customer.builder()
                        .uuid(uuid)
                        .build())
                .message(requestDTO.getMessage())
                .build();
    }

    public SchedulingResponseDTO mapper(final Scheduling scheduling) {
        return SchedulingResponseDTO.builder()
                .uuid(scheduling.getUuid())
                .send(scheduling.getSend())
                .message(scheduling.getMessage())
                .receiver(getReceiver(scheduling.getReceiver()))
                .schedulingEmail(getSchedulingEmail(scheduling.getSchedulingEmail()))
                .schedulingSms(getSchedulingSms(scheduling.getSchedulingSms()))
                .schedulingPush(getSchedulingPush(scheduling.getSchedulingPush()))
                .schedulingWhatsapp(getSchedulingWhatsapp(scheduling.getSchedulingWhatsapp()))
                .build();
    }

    public SchedulingTypesStatusResponseDTO mapperStatus(final Scheduling scheduling) {
        return SchedulingTypesStatusResponseDTO.builder()
                .uuid(scheduling.getUuid())
                .schedulingEmail(getSchedulingEmail(scheduling.getSchedulingEmail()))
                .schedulingSms(getSchedulingSms(scheduling.getSchedulingSms()))
                .schedulingPush(getSchedulingPush(scheduling.getSchedulingPush()))
                .schedulingWhatsapp(getSchedulingWhatsapp(scheduling.getSchedulingWhatsapp()))
                .build();
    }

    protected SchedulingResponseDTO.SchedulingType getSchedulingType(final SchedulingEmail  schedulingEmail) {
        return SchedulingResponseDTO.SchedulingType.builder()
                .uuid(schedulingEmail.getUuid())
                .sended(schedulingEmail.getSended())
                .status(Optional.ofNullable(schedulingEmail.getStatus())
                        .map(status -> SchedulingResponseDTO.Status.builder()
                                .id(status.getId())
                                .name(status.getName())
                                .build())
                        .orElse(null))
                .build();
    }

    protected SchedulingResponseDTO.SchedulingType getSchedulingType(final SchedulingSms  schedulingSms) {
        return SchedulingResponseDTO.SchedulingType.builder()
                .uuid(schedulingSms.getUuid())
                .sended(schedulingSms.getSended())
                .status(Optional.ofNullable(schedulingSms.getStatus())
                        .map(status -> SchedulingResponseDTO.Status.builder()
                                .id(status.getId())
                                .name(status.getName())
                                .build())
                        .orElse(null))
                .build();
    }

    protected SchedulingResponseDTO.SchedulingType getSchedulingType(final SchedulingPush  schedulingPush) {
        return SchedulingResponseDTO.SchedulingType.builder()
                .uuid(schedulingPush.getUuid())
                .sended(schedulingPush.getSended())
                .status(Optional.ofNullable(schedulingPush.getStatus())
                        .map(status -> SchedulingResponseDTO.Status.builder()
                                .id(status.getId())
                                .name(status.getName())
                                .build())
                        .orElse(null))
                .build();
    }

    protected SchedulingResponseDTO.SchedulingType getSchedulingType(final SchedulingWhatsapp  schedulingWhatsapp) {
        return SchedulingResponseDTO.SchedulingType.builder()
                .uuid(schedulingWhatsapp.getUuid())
                .sended(schedulingWhatsapp.getSended())
                .status(Optional.ofNullable(schedulingWhatsapp.getStatus())
                        .map(status -> SchedulingResponseDTO.Status.builder()
                                .id(status.getId())
                                .name(status.getName())
                                .build())
                        .orElse(null))
                .build();
    }

    protected SchedulingResponseDTO.SchedulingType getSchedulingWhatsapp(final SchedulingWhatsapp scheduling) {
        return Optional.ofNullable(scheduling)
                .map(schedulingWhatsapp -> getSchedulingType(schedulingWhatsapp))
                .orElse(null);
    }

    protected SchedulingResponseDTO.SchedulingType getSchedulingPush(final SchedulingPush scheduling) {
        return Optional.ofNullable(scheduling)
                .map(schedulingPush -> getSchedulingType(schedulingPush))
                .orElse(null);
    }

    protected SchedulingResponseDTO.SchedulingType getSchedulingSms(final SchedulingSms scheduling) {
        return Optional.ofNullable(scheduling)
                .map(schedulingSms -> getSchedulingType(schedulingSms))
                .orElse(null);
    }

    protected SchedulingResponseDTO.SchedulingType getSchedulingEmail(final SchedulingEmail scheduling) {
        return Optional.ofNullable(scheduling)
                .map(schedulingEmail -> getSchedulingType(schedulingEmail))
                .orElse(null);
    }

    protected SchedulingResponseDTO.Receiver getReceiver(final Customer customer) {
        return Optional.ofNullable(customer)
                .map(receiver -> SchedulingResponseDTO.Receiver.builder()
                        .uuid(receiver.getUuid())
                        .name(receiver.getName())
                        .email(receiver.getEmail())
                        .phone(receiver.getPhone())
                        .push(receiver.getPush())
                        .whatsapp(receiver.isWhatsapp())
                        .build())
                .orElse(null);
    }
}
