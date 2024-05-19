package com.jamith.tlms.remote;

import com.jamith.tlms.dto.request.orderService.AddOrderRequest;
import com.jamith.tlms.dto.response.orderService.AddOrderResponse;
import jakarta.ejb.Remote;

@Remote
public interface OrderService {
    AddOrderResponse makeOrder(AddOrderRequest addOrderRequest);
}
