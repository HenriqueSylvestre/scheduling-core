package com.magalutest.schedulingcore.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
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

    private LocalDateTime send;
    private String message;

    @ManyToOne
    @JoinColumn(name = "customer_uuid")
    private Customer receiver;

    @OneToOne(cascade={CascadeType.PERSIST, CascadeType.REMOVE})
    @JoinColumn(name = "scheduling_email_uuid")
    private SchedulingEmail schedulingEmail;

    @OneToOne(cascade={CascadeType.PERSIST, CascadeType.REMOVE})
    @JoinColumn(name = "scheduling_sms_uuid")
    private SchedulingSms schedulingSms;

    @OneToOne(cascade={CascadeType.PERSIST, CascadeType.REMOVE})
    @JoinColumn(name = "scheduling_push_uuid")
    private SchedulingPush schedulingPush;

    @OneToOne(cascade={CascadeType.PERSIST, CascadeType.REMOVE})
    @JoinColumn(name = "scheduling_whatsapp_uuid")
    private SchedulingWhatsapp schedulingWhatsapp;
}
