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
@Table(name = "scheduling_whatsapp")
public class SchedulingWhatsapp {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @org.hibernate.annotations.Type(type = "uuid-char")
    private UUID uuid;

    private ZonedDateTime sended;

    @ManyToOne
    @JoinColumn(name = "status_id")
    private Status status;
}
