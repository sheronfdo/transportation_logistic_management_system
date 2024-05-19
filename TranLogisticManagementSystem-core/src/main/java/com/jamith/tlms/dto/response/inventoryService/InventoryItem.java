package com.jamith.tlms.dto.response.inventoryService;

import lombok.Data;

import java.io.Serializable;

@Data
public class InventoryItem implements Serializable {
    private Integer id;
    private String name;
    private Integer stockQty;
    private Double price;
    private String status;
}
