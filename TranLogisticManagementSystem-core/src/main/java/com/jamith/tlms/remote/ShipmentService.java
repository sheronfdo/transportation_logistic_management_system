package com.jamith.tlms.remote;

import com.jamith.tlms.dto.request.shipmentService.GetShipmentRequest;
import com.jamith.tlms.dto.request.shipmentService.RouteOptimizeRequest;
import com.jamith.tlms.dto.request.shipmentService.ShipmentStartRequest;
import com.jamith.tlms.dto.response.shipmentService.GetShipmentResponse;
import com.jamith.tlms.dto.response.shipmentService.RouteOptimizeResponse;
import com.jamith.tlms.dto.response.shipmentService.ShipmentStartResponse;
import jakarta.ejb.Remote;

import java.util.List;

@Remote
public interface ShipmentService {
    void shipmentOrganizer();
    GetShipmentResponse getShipment(GetShipmentRequest request);
    ShipmentStartResponse shipmentStart(ShipmentStartRequest request);
    RouteOptimizeResponse getOrdersWithOptimizedRoute(RouteOptimizeRequest request);
}
