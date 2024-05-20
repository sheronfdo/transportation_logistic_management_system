package com.jamith.tlms.dto.response.shipmentService;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class RouteOptimizeResponse implements Serializable {
    List<RouteOptimizeOrderResponse> routeOptimizeOrderResponses;
}
