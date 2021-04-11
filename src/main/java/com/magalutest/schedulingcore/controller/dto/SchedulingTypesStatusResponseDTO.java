package com.magalutest.schedulingcore.controller.dto;

import lombok.*;

import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class SchedulingTypesStatusResponseDTO implements Serializable {
    private static final long serialVersionUID = 5893124520125373620L;
    private UUID uuid;
    private SchedulingResponseDTO.SchedulingType schedulingEmail;
    private SchedulingResponseDTO.SchedulingType schedulingSms;
    private SchedulingResponseDTO.SchedulingType schedulingPush;
    private SchedulingResponseDTO.SchedulingType schedulingWhatsapp;
}
