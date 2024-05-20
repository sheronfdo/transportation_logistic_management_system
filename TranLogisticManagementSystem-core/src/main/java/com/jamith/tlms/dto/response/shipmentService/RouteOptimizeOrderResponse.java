package com.jamith.tlms.dto.response.shipmentService;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class RouteOptimizeOrderResponse implements Serializable {
    private Integer id;
    private Date date;
    private String user;
    private Double totalAmount;
    private String address;
    private Double latitude;
    private Double longitude;
    private String status;
}
