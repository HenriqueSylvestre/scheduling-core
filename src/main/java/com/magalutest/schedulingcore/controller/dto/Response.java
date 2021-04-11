package com.magalutest.schedulingcore.controller.dto;

import lombok.*;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class Response implements Serializable {

    private static final long serialVersionUID = 2158742599429859744L;
    private List<?> data;
}
