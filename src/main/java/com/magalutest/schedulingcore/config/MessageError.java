package com.magalutest.schedulingcore.config;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MessageError implements Serializable {
    private static final long serialVersionUID = 3172175781521537345L;
    private String error;
    private String message;
    private int code;
}
