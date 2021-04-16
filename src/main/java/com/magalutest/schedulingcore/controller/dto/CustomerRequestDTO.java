package com.magalutest.schedulingcore.controller.dto;

import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class CustomerRequestDTO implements Serializable {
    private static final long serialVersionUID = 3425205713170850061L;

    @NotNull
    @Size(max = 128)
    private String name;

    @Size(max = 128)
    private String email;

    @Size(max = 13)
    private String phone;

    @Size(max = 36)
    private String push;
    private boolean whatsapp;
}
