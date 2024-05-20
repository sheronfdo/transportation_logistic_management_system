package com.jamith.tlms.dto.request.orderService;

import lombok.Data;

import java.io.Serializable;

@Data
public class CompleteOrderRequest implements Serializable {
    Integer orderId;
}
