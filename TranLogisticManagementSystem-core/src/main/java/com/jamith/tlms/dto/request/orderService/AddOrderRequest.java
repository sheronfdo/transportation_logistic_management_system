package com.jamith.tlms.dto.request.orderService;

import lombok.Data;

import java.io.Serializable;

@Data
public class AddOrderRequest implements Serializable {
    Integer inventoryItemId;
    Integer itemQty;
    String address;
    Double latitude;
    Double longitude;
}
