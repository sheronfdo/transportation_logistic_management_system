package com.jamith.tlms.remote;

import com.jamith.tlms.dto.request.inventoryService.AddNewItemRequest;
import com.jamith.tlms.dto.request.inventoryService.GetItemsRequest;
import com.jamith.tlms.dto.response.inventoryService.AddNewItemResponse;
import com.jamith.tlms.dto.response.inventoryService.GetItemsResponse;
import jakarta.ejb.Remote;

@Remote
public interface InventoryService {
    AddNewItemResponse addNewItem(AddNewItemRequest addNewItemRequest);
    GetItemsResponse getItems(GetItemsRequest getItemsRequest);
    GetItemsResponse getSingleItem(GetItemsRequest getItemsRequest);

}
