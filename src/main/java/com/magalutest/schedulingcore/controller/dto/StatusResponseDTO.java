package com.magalutest.schedulingcore.controller.dto;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class StatusResponseDTO implements Serializable {
    private static final long serialVersionUID = -7639393524818956151L;
    private long id;
    private String name;
}
