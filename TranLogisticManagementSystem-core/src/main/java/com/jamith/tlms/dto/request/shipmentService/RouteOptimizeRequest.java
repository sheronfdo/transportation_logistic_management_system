package com.jamith.tlms.dto.request.shipmentService;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class RouteOptimizeRequest implements Serializable {
    Integer shipmentId;
    Double startLat;
    Double startLon;
}
