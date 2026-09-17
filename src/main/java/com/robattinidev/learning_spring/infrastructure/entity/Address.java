package com.robattinidev.learning_spring.infrastructure.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "address")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    @Column(name = "street")
    private String street;
    @Column(name = "number")
    private Long number;
    @Column(name = "complement", length = 15)
    private String complement;
    @Column(name = "ciy", length = 150)
    private String city;
    @Column(name = "state", length = 2)
    private String state;
    @Column(name = "cep", length = 9)
    private String cep;

}

