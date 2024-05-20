package com.jamith.tlms.serviceImpl;

import com.jamith.tlms.dto.request.inventoryService.GetItemsRequest;
import com.jamith.tlms.dto.response.inventoryService.GetItemsResponse;
import com.jamith.tlms.dto.response.inventoryService.InventoryItem;
import com.jamith.tlms.entity.Inventory;
import com.jamith.tlms.remote.InventoryService;
import com.jamith.tlms.dto.request.inventoryService.AddNewItemRequest;
import com.jamith.tlms.dto.response.inventoryService.AddNewItemResponse;
import jakarta.annotation.security.RolesAllowed;
import jakarta.ejb.Stateless;
import jakarta.ejb.TransactionAttribute;
import jakarta.ejb.TransactionAttributeType;
import com.jamith.tlms.util.enums.Status;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;

@Stateless
public class InventoryServiceBean implements InventoryService {

    @PersistenceContext(unitName = "tranLogisticSys")
    private EntityManager em;

    @Override
    @RolesAllowed("admin")
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

    @RolesAllowed({"admin", "driver", "customer"})
    @Override
    public GetItemsResponse getItems(GetItemsRequest getItemsRequest) {
        ModelMapper modelMapper = new ModelMapper();
        List<InventoryItem> query = em.createQuery("SELECT i FROM Inventory i WHERE i.status=:status", Inventory.class)
                .setParameter("status", Status.active.name())
                .getResultList().stream().map(e->modelMapper.map(e, InventoryItem.class)).collect(Collectors.toList());

        GetItemsResponse getItemsResponse = new GetItemsResponse();
        getItemsResponse.setInventoryList(query);
        return getItemsResponse;
    }

    @Override
    @RolesAllowed({"admin", "driver", "customer"})
    public GetItemsResponse getSingleItem(GetItemsRequest getItemsRequest) {
        ModelMapper modelMapper = new ModelMapper();
        Inventory item = em.createQuery("SELECT i FROM Inventory i WHERE i.status=:status and i.id=:id", Inventory.class)
                .setParameter("status", Status.active.name())
                .setParameter("id", getItemsRequest.getItemId())
                .getSingleResult();

        GetItemsResponse getItemsResponse = new GetItemsResponse();
        getItemsResponse.setInventoryItem(modelMapper.map(item, InventoryItem.class));
        return getItemsResponse;
    }
}
