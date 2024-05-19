package com.jamith.tlms.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@Entity
@Table(name = "inventory")
public class Inventory implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "name", nullable = false, length = 45)
    private String name;

    @Column(name = "stockQty", nullable = false)
    private Integer stockQty;

    @Column(name = "price", nullable = false)
    private Double price;

    @Column(name = "status", nullable = false, length = 45)
    private String status;

}