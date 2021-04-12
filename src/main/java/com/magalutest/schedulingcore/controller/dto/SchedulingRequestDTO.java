package com.magalutest.schedulingcore.controller.dto;

import lombok.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class SchedulingRequestDTO implements Serializable {
    private static final long serialVersionUID = -834958310503210681L;

    @NotNull
    private LocalDateTime send;

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
        @NotNull
        private UUID uuid;
    }
}
