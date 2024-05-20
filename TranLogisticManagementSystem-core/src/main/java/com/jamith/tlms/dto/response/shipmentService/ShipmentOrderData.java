package com.jamith.tlms.dto.response.shipmentService;

import lombok.Data;

import java.io.Serializable;

@Data
public class ShipmentOrderData implements Serializable {
    private Integer id;
    private String deliveryStatus;
    private String status;
    private Integer shipment;
    private Integer order;
}
