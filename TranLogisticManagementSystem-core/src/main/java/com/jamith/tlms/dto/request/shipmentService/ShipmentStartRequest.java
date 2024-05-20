package com.jamith.tlms.dto.request.shipmentService;

import lombok.Data;

import java.io.Serializable;

@Data
public class ShipmentStartRequest implements Serializable {
    Integer shipmentId;
}
