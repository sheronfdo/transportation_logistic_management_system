package com.jamith.tlms.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "shipment")
public class Shipment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "driver", length = 45)
    private String driver;

    @Column(name = "vehicle", length = 45)
    private String vehicle;

    @Column(name = "date", length = 100)
    private Date date;

    @Column(name = "status", length = 45)
    private String status;

}