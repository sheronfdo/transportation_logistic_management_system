package com.jamith.tlms.dto.response.orderService;

import lombok.Data;

import java.io.Serializable;

@Data
public class AddOrderResponse implements Serializable {
    String description;
    Integer orderId;
    Double totalAmount;
}
