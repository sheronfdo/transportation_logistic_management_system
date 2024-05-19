package com.jamith.tlms.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "shipment")
public class Shipment implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "driver", length = 45)
    private String driver;

    @Column(name = "vehicle", length = 45)
    private String vehicle;

    @Column(name = "date")
    private Date date;

    @Column(name = "status", length = 45)
    private String status;

}