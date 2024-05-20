package com.jamith.tlms.serviceImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

import com.jamith.tlms.dto.request.shipmentService.GetShipmentRequest;
import com.jamith.tlms.dto.request.shipmentService.RouteOptimizeRequest;
import com.jamith.tlms.dto.request.shipmentService.ShipmentStartRequest;
import com.jamith.tlms.dto.response.shipmentService.GetShipmentResponse;
import com.jamith.tlms.dto.response.shipmentService.ShipmentStartResponse;
import com.jamith.tlms.entity.Order;
import com.jamith.tlms.entity.Shipment;
import com.jamith.tlms.entity.ShipmentOrder;
import com.jamith.tlms.remote.ShipmentService;
import com.jamith.tlms.util.enums.ShipmentStatus;
import com.jamith.tlms.util.enums.Status;
import jakarta.ejb.EJB;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

class ShipmentServiceBeanTest {
    @Mock
    private EntityManager em;

    @InjectMocks
    private ShipmentServiceBean shipmentServiceBean;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testShipmentOrganizer() {
        // Arrange
        List<Order> orders = new ArrayList<>();
        Order order = new Order();
        order.setId(1);
        order.setStatus(Status.active.name());
        orders.add(order);

        when(em.createQuery(anyString(), eq(Order.class))).thenReturn((TypedQuery<Order>) mock(javax.persistence.TypedQuery.class));
        when(em.createQuery(anyString(), eq(Order.class)).setParameter(anyString(), anyString())).thenReturn((TypedQuery<Order>) mock(javax.persistence.TypedQuery.class));
        when(em.createQuery(anyString(), eq(Order.class)).setParameter(anyString(), anyString()).getResultList()).thenReturn(orders);

        shipmentServiceBean.shipmentOrganizer();

        verify(em).persist(any(Shipment.class));
        verify(em).persist(any(ShipmentOrder.class));
        verify(em).persist(any(Order.class));
    }

    @Test
    void testGetShipment() {
        // Arrange
        List<Shipment> shipments = new ArrayList<>();
        Shipment shipment = new Shipment();
        shipment.setId(1);
        shipment.setStatus(Status.active.name());
        shipments.add(shipment);

        when(em.createQuery(anyString(), eq(Shipment.class))).thenReturn((TypedQuery<Shipment>) mock(javax.persistence.TypedQuery.class));
        when(em.createQuery(anyString(), eq(Shipment.class)).setParameter(anyString(), anyString())).thenReturn((TypedQuery<Shipment>) mock(javax.persistence.TypedQuery.class));
        when(em.createQuery(anyString(), eq(Shipment.class)).setParameter(anyString(), anyString()).getResultList()).thenReturn(shipments);

        GetShipmentRequest request = new GetShipmentRequest();

        GetShipmentResponse response = shipmentServiceBean.getShipment(request);

        assertEquals(1, response.getShipmentData().size());
        assertEquals(shipment.getId(), response.getShipmentData().get(0).getId());
    }

    @Test
    void testShipmentStart() {
        Shipment shipment = new Shipment();
        shipment.setId(1);
        shipment.setStatus(Status.active.name());

        List<ShipmentOrder> shipmentOrders = new ArrayList<>();
        ShipmentOrder shipmentOrder = new ShipmentOrder();
        shipmentOrder.setId(1);
        shipmentOrder.setStatus(Status.active.name());
        Status ShipmentStatus;
        shipmentOrder.setDeliveryStatus(com.jamith.tlms.util.enums.ShipmentStatus.pending.name());
        shipmentOrder.setShipment(shipment);
        shipmentOrders.add(shipmentOrder);

        when(em.createQuery(anyString(), eq(Shipment.class))).thenReturn((TypedQuery<Shipment>) mock(javax.persistence.TypedQuery.class));
        when(em.createQuery(anyString(), eq(Shipment.class)).setParameter(anyString(), anyInt())).thenReturn((TypedQuery<Shipment>) mock(javax.persistence.TypedQuery.class));
        when(em.createQuery(anyString(), eq(Shipment.class)).setParameter(anyString(), anyInt()).getSingleResult()).thenReturn(shipment);

        when(em.createQuery(anyString(), eq(ShipmentOrder.class))).thenReturn((TypedQuery<ShipmentOrder>) mock(javax.persistence.TypedQuery.class));
        when(em.createQuery(anyString(), eq(ShipmentOrder.class)).setParameter(anyString(), anyInt())).thenReturn((TypedQuery<ShipmentOrder>) mock(javax.persistence.TypedQuery.class));
        when(em.createQuery(anyString(), eq(ShipmentOrder.class)).setParameter(anyString(), anyInt()).setParameter(anyString(), anyString())).thenReturn((TypedQuery<ShipmentOrder>) mock(javax.persistence.TypedQuery.class));
        when(em.createQuery(anyString(), eq(ShipmentOrder.class)).setParameter(anyString(), anyInt()).setParameter(anyString(), anyString()).getResultList()).thenReturn(shipmentOrders);

        ShipmentStartRequest request = new ShipmentStartRequest();
        request.setShipmentId(1);

        ShipmentStartResponse response = shipmentServiceBean.shipmentStart(request);

        assertEquals(1, response.getShipmentOrderDataList().size());
        assertEquals("Shipment started", response.getDescription());
        assertEquals(shipment.getId(), response.getShipmentId());
        verify(em).persist(shipment);
    }
}
