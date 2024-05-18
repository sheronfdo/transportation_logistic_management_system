package com.jamith.tlms.dto.request.inventoryService;

import lombok.Data;

import java.io.Serializable;

@Data
public class AddNewItemRequest implements Serializable {
    private String name;
    private Integer stockQty;
    private Double price;
}
