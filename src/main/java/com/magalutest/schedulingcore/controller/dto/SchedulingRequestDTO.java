package com.magalutest.schedulingcore.controller.dto;

import lombok.*;

import javax.validation.Valid;
import javax.validation.constraints.AssertFalse;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class SchedulingRequestDTO implements Serializable {
    private static final long serialVersionUID = -834958310503210681L;

    @NotBlank
    @NotNull
    private ZonedDateTime send;

    @NotBlank
    @NotNull
    private String message;

    @Valid
    @NotNull
    private Receiver receiver;

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @ToString
    @Builder
    public static class Receiver implements Serializable {
        private static final long serialVersionUID = 7284693890535044733L;
        @NotBlank
        @NotNull
        private UUID uuid;
    }
}
