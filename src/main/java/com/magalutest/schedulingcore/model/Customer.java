package com.magalutest.schedulingcore.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(of = "uuid")
@Builder
@Entity
@Table(name = "customer")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @org.hibernate.annotations.Type(type = "uuid-char")
    private UUID uuid;

    @Size(max = 128)
    private String name;

    @Size(max = 128)
    private String email;

    @Size(max = 13)
    private String phone;

    private String push;
    private boolean whatsapp;
}
