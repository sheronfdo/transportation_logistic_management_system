package com.jamith.tlms.serviceImpl;


import com.jamith.tlms.dto.request.orderService.CompleteOrderRequest;
import com.jamith.tlms.dto.response.orderService.CompleteOrderResponse;
import com.jamith.tlms.entity.Order;
import com.jamith.tlms.util.enums.ShipmentStatus;
import com.jamith.tlms.util.enums.Status;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class OrderServiceBeanTest {

    @Mock
    private EntityManager em;

    @InjectMocks
    private OrderServiceBean orderServiceBean;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCompleteOrder() {

        CompleteOrderRequest completeOrderRequest = new CompleteOrderRequest();
        completeOrderRequest.setOrderId(1);

        Order order = new Order();
        order.setId(1);
        order.setStatus(Status.active.name());

        when(em.find(Order.class, completeOrderRequest.getOrderId())).thenReturn(order);

        CompleteOrderResponse response = orderServiceBean.completeOrder(completeOrderRequest);

        assertEquals("Order Completed", response.getDescription());
        assertEquals(ShipmentStatus.delivered.name(), order.getStatus());

        verify(em).find(Order.class, completeOrderRequest.getOrderId());
        verify(em).persist(order);
    }
}
