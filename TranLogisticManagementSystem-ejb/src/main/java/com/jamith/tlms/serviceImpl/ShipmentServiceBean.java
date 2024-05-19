package com.jamith.tlms.serviceImpl;

import com.jamith.tlms.entity.Inventory;
import com.jamith.tlms.entity.Order;
import com.jamith.tlms.entity.Shipment;
import com.jamith.tlms.entity.ShipmentOrder;
import com.jamith.tlms.remote.ShipmentService;
import com.jamith.tlms.util.enums.ShipmentStatus;
import com.jamith.tlms.util.enums.Status;
import jakarta.ejb.Schedule;
import jakarta.ejb.Stateless;
import jakarta.ejb.TransactionAttribute;
import jakarta.ejb.TransactionAttributeType;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.Date;
import java.util.List;

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
}
