package com.magalutest.schedulingcore.controller.dto;

import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class SchedulingResponseDTO implements Serializable {

    private static final long serialVersionUID = -2051426503766976485L;
    private UUID uuid;
    private LocalDateTime send;
    private String message;
    private Receiver receiver;
    private SchedulingType schedulingEmail;
    private SchedulingType schedulingSms;
    private SchedulingType schedulingPush;
    private SchedulingType schedulingWhatsapp;

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @ToString
    @Builder
    public static class Receiver implements Serializable {
        private static final long serialVersionUID = -4245034484810624958L;
        private UUID uuid;
        private String name;
        private String email;
        private String phone;
        private String push;
        private boolean whatsapp;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @ToString
    @Builder
    public static class SchedulingType implements Serializable {
        private static final long serialVersionUID = 2901307603872833341L;
        private UUID uuid;
        private LocalDateTime sended;
        private Status status;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @ToString
    @Builder
    public static class Status implements Serializable {
        private static final long serialVersionUID = -6817103066163591863L;
        private int id;
        private String name;
    }
}
