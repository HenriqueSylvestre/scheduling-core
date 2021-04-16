package com.magalutest.schedulingcore.controller.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class StatusRequestDTO implements Serializable {
    private static final long serialVersionUID = 1889319679972905361L;

    @NotBlank
    @Size(max = 45)
    private String name;
}
