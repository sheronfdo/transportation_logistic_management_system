package com.jamith.tlms.serviceImpl;

import com.jamith.tlms.dto.request.orderService.AddOrderRequest;
import com.jamith.tlms.dto.response.inventoryService.InventoryItem;
import com.jamith.tlms.dto.response.orderService.AddOrderResponse;
import com.jamith.tlms.entity.Inventory;
import com.jamith.tlms.entity.Order;
import com.jamith.tlms.entity.OrderItem;
import com.jamith.tlms.remote.OrderService;
import com.jamith.tlms.util.enums.Status;
import jakarta.ejb.Stateless;
import jakarta.ejb.TransactionAttribute;
import jakarta.ejb.TransactionAttributeType;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.modelmapper.ModelMapper;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Stateless
public class OrderServiceBean implements OrderService {

    @PersistenceContext(unitName = "tranLogisticSys")
    private EntityManager em;

    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public AddOrderResponse makeOrder(AddOrderRequest addOrderRequest) {
        Inventory item = em.createQuery("SELECT i FROM Inventory i WHERE i.status=:status and i.id=:id", Inventory.class)
                .setParameter("status", Status.active.name())
                .setParameter("id", addOrderRequest.getInventoryItemId())
                .getSingleResult();

        Order order = new Order();
        order.setAddress(addOrderRequest.getAddress());
        order.setDate(new Date());
        order.setUser("sample");
        order.setLatitude(addOrderRequest.getLatitude());
        order.setLongitude(addOrderRequest.getLongitude());
        order.setTotalAmount(item.getPrice() * addOrderRequest.getItemQty());
        order.setStatus(Status.active.name());
        em.persist(order);

        OrderItem orderItem = new OrderItem();
        orderItem.setOrder(order);
        orderItem.setQty(addOrderRequest.getItemQty());
        orderItem.setInventory(item);
        orderItem.setAmount(item.getPrice() * addOrderRequest.getItemQty());
        orderItem.setStatus(Status.active.name());

        em.persist(orderItem);

        item.setStockQty(item.getStockQty() - addOrderRequest.getItemQty());
        em.persist(item);

        AddOrderResponse addOrderResponse = new AddOrderResponse();
        addOrderResponse.setDescription("Make Order Successfully!");
        addOrderResponse.setOrderId(order.getId());
        addOrderResponse.setTotalAmount(order.getTotalAmount());
        return addOrderResponse;
    }
}
