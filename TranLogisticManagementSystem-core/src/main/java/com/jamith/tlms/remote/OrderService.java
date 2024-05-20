package com.jamith.tlms.remote;

import com.jamith.tlms.dto.request.orderService.AddOrderRequest;
import com.jamith.tlms.dto.request.orderService.CompleteOrderRequest;
import com.jamith.tlms.dto.response.orderService.AddOrderResponse;
import com.jamith.tlms.dto.response.orderService.CompleteOrderResponse;
import jakarta.ejb.Remote;

@Remote
public interface OrderService {
    AddOrderResponse makeOrder(AddOrderRequest addOrderRequest);
    CompleteOrderResponse completeOrder(CompleteOrderRequest completeOrderRequest);
}
