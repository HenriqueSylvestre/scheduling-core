package com.magalutest.schedulingcore.controller.dto;

import lombok.*;

import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class CustomerResponseDTO implements Serializable {
    private static final long serialVersionUID = -1465417033965706940L;

    private UUID uuid;
    private String name;
    private String email;
    private String phone;
    private String push;
    private boolean whatsapp;
}
