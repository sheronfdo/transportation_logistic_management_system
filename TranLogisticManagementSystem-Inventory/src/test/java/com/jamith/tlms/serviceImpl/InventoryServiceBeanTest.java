package com.jamith.tlms.serviceImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import com.jamith.tlms.dto.request.inventoryService.AddNewItemRequest;
import com.jamith.tlms.dto.request.inventoryService.GetItemsRequest;

import java.util.ArrayList;
import java.util.List;

import com.jamith.tlms.dto.response.inventoryService.AddNewItemResponse;
import com.jamith.tlms.dto.response.inventoryService.GetItemsResponse;
import com.jamith.tlms.entity.Inventory;
import com.jamith.tlms.util.enums.Status;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class InventoryServiceBeanTest {

    @Mock
    private EntityManager em;

    @InjectMocks
    private InventoryServiceBean inventoryServiceBean;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddNewItem() {
        AddNewItemRequest request = new AddNewItemRequest();
        request.setName("Test Item");
        request.setPrice(100.0);
        request.setStockQty(10);

        AddNewItemResponse response = inventoryServiceBean.addNewItem(request);

        assertEquals("Item Added Successfully", response.getDescription());
        verify(em).persist(any(Inventory.class));
    }

    @Test
    void testGetItems() {
        List<Inventory> inventories = new ArrayList<>();
        Inventory inventory = new Inventory();
        inventory.setId(1);
        inventory.setName("Test Item");
        inventory.setStatus(Status.active.name());
        inventories.add(inventory);

        TypedQuery<Inventory> queryMock = mock(TypedQuery.class);
        when(em.createQuery(anyString(), eq(Inventory.class))).thenReturn(queryMock);
        when(queryMock.setParameter(anyString(), anyString())).thenReturn(queryMock);
        when(queryMock.getResultList()).thenReturn(inventories);

        GetItemsRequest request = new GetItemsRequest();

        GetItemsResponse response = inventoryServiceBean.getItems(request);

        assertEquals(1, response.getInventoryList().size());
        assertEquals(inventory.getName(), response.getInventoryList().get(0).getName());
    }

    @Test
    void testGetSingleItem() {
        Inventory inventory = new Inventory();
        inventory.setId(1);
        inventory.setName("Test Item");
        inventory.setStatus(Status.active.name());

        TypedQuery<Inventory> queryMock = mock(TypedQuery.class);
        when(em.createQuery(anyString(), eq(Inventory.class))).thenReturn(queryMock);
        when(queryMock.setParameter(anyString(), anyString())).thenReturn(queryMock);
        when(queryMock.setParameter(anyString(), anyInt())).thenReturn(queryMock);
        when(queryMock.getSingleResult()).thenReturn(inventory);

        GetItemsRequest request = new GetItemsRequest();
        request.setItemId(1);

        GetItemsResponse response = inventoryServiceBean.getSingleItem(request);

        assertEquals(inventory.getName(), response.getInventoryItem().getName());
    }
}
