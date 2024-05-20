package com.jamith.tlms.dto.response.shipmentService;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class ShipmentData implements Serializable {
    private Integer id;
    private String driver;
    private String vehicle;
    private Date date;
}
