package com.jamith.tlms.serviceImpl;

import com.jamith.tlms.dto.request.shipmentService.GetShipmentRequest;
import com.jamith.tlms.dto.request.shipmentService.RouteOptimizeRequest;
import com.jamith.tlms.dto.request.shipmentService.ShipmentStartRequest;
import com.jamith.tlms.dto.response.shipmentService.*;
import com.jamith.tlms.entity.Inventory;
import com.jamith.tlms.entity.Order;
import com.jamith.tlms.entity.Shipment;
import com.jamith.tlms.entity.ShipmentOrder;
import com.jamith.tlms.interceptor.RouteOptimizationInterceptor;
import com.jamith.tlms.remote.ShipmentService;
import com.jamith.tlms.util.enums.ShipmentStatus;
import com.jamith.tlms.util.enums.Status;
import jakarta.ejb.Schedule;
import jakarta.ejb.Stateless;
import jakarta.ejb.TransactionAttribute;
import jakarta.ejb.TransactionAttributeType;
import jakarta.interceptor.Interceptors;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.modelmapper.ModelMapper;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Stateless
public class ShipmentServiceBean implements ShipmentService {

    @PersistenceContext(unitName = "tranLogisticSys")
    private EntityManager em;

    @Override
    @Schedule(persistent = true, hour = "*", minute = "*/5", second = "*")
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void shipmentOrganizer() {
        List<Order> orders = em.createQuery("SELECT i FROM Order i WHERE i.status=:status", Order.class)
                .setParameter("status", Status.active.name())
                .getResultList();
        if (orders.size() > 0) {
            Shipment shipment = new Shipment();
            shipment.setDate(new Date());
            shipment.setDriver("sample driver");
            shipment.setVehicle("Lorry");
            shipment.setStatus(Status.active.name());
            em.persist(shipment);
            orders.forEach(e -> {
                ShipmentOrder shipmentOrder = new ShipmentOrder();
                shipmentOrder.setShipment(shipment);
                shipmentOrder.setOrder(e);
                shipmentOrder.setDeliveryStatus(ShipmentStatus.pending.name());
                shipmentOrder.setStatus(Status.active.name());
                em.persist(shipmentOrder);
                e.setStatus(ShipmentStatus.readyToShipment.name());
                em.persist(e);
            });
        }
    }

    @Override
    public GetShipmentResponse getShipment(GetShipmentRequest request) {
        ModelMapper modelMapper = new ModelMapper();
        List<ShipmentData> shipments = em.createQuery("SELECT i FROM Shipment i WHERE i.status=:status", Shipment.class)
                .setParameter("status", Status.active.name())
                .getResultList().stream().map(e -> modelMapper.map(e, ShipmentData.class)).collect(Collectors.toList());
        GetShipmentResponse response = new GetShipmentResponse();
        response.setShipmentData(shipments);
        return response;
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public ShipmentStartResponse shipmentStart(ShipmentStartRequest request) {
        ModelMapper modelMapper = new ModelMapper();
        Shipment shipment = em.createQuery("SELECT i FROM Shipment i WHERE i.id=:id", Shipment.class)
                .setParameter("id", request.getShipmentId())
                .getSingleResult();

        shipment.setStatus(ShipmentStatus.inProgress.name());
        em.persist(shipment);

        List<ShipmentOrderData> shipments = em.createQuery("SELECT i FROM ShipmentOrder i WHERE i.shipment.id=:shipment and i.status=:status", ShipmentOrder.class)
                .setParameter("shipment", request.getShipmentId())
                .setParameter("status", Status.active.name())
                .getResultList().stream().map(e -> {
                    ShipmentOrderData shipmentOrderData = new ShipmentOrderData();
                    shipmentOrderData.setId(e.getId());
                    shipmentOrderData.setStatus(e.getStatus());
                    shipmentOrderData.setDeliveryStatus(e.getDeliveryStatus());
                    shipmentOrderData.setOrder(e.getOrder().getId());
                    shipmentOrderData.setShipment(e.getShipment().getId());
                    return shipmentOrderData;
                }).collect(Collectors.toList());

        ShipmentStartResponse shipmentStartResponse = new ShipmentStartResponse();
        shipmentStartResponse.setShipmentId(shipment.getId());
        shipmentStartResponse.setDescription("Shipment started");
        shipmentStartResponse.setShipmentOrderDataList(shipments);
        return shipmentStartResponse;
    }

    @Interceptors(RouteOptimizationInterceptor.class)
    public RouteOptimizeResponse getOrdersWithOptimizedRoute(RouteOptimizeRequest request) {
        // This method will be intercepted, and the route optimization logic will be applied
        return null;
    }
}
