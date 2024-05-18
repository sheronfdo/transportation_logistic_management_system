package com.jamith.tlms.serviceImpl;

import com.jamith.tlms.entity.Inventory;
import com.jamith.tlms.remote.InventoryService;
import com.jamith.tlms.dto.request.inventoryService.AddNewItemRequest;
import com.jamith.tlms.dto.response.inventoryService.AddNewItemResponse;
import jakarta.ejb.Stateless;
import jakarta.ejb.TransactionAttribute;
import jakarta.ejb.TransactionAttributeType;
import com.jamith.tlms.util.enums.Status;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Stateless
public class InventoryServiceBean implements InventoryService {

    @PersistenceContext(unitName = "tranLogisticSys")
    private EntityManager em;

    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public AddNewItemResponse addNewItem(AddNewItemRequest addNewItemRequest) {

        if (em == null) {
            throw new IllegalStateException("EntityManager is not injected");
        }

        Inventory inventory = new Inventory();
        inventory.setName(addNewItemRequest.getName());
        inventory.setPrice(addNewItemRequest.getPrice());
        inventory.setStockQty(addNewItemRequest.getStockQty());
        inventory.setStatus(Status.active.name());
        em.persist(inventory);
        return new AddNewItemResponse("Item Added Successfully");
    }
}
