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
@Table(name = "scheduling_email")
public class SchedulingEmail {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @org.hibernate.annotations.Type(type = "uuid-char")
    private UUID uuid;

    private LocalDateTime sended;

    @ManyToOne
    @JoinColumn(name = "status_id")
    private Status status;
}
