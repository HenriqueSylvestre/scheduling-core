package com.magalutest.schedulingcore.model;

import lombok.*;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(of = "uuid")
@Builder
@Entity
@Table(name = "scheduling")
public class Scheduling {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @org.hibernate.annotations.Type(type = "uuid-char")
    private UUID uuid;

    private ZonedDateTime send;
    private String message;

    @ManyToOne
    @JoinColumn(name = "costumer_uuid")
    private Costumer receiver;

    @OneToOne
    @JoinColumn(name = "scheduling_email_uuid")
    private SchedulingEmail schedulingEmail;

    @OneToOne
    @JoinColumn(name = "scheduling_sms_uuid")
    private SchedulingSms schedulingSms;

    @OneToOne
    @JoinColumn(name = "scheduling_push_uuid")
    private SchedulingPush schedulingPush;

    @OneToOne
    @JoinColumn(name = "scheduling_whatsapp_uuid")
    private SchedulingWhatsapp schedulingWhatsapp;
}
