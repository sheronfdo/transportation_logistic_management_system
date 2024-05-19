package com.jamith.tlms.dto.response.inventoryService;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class GetItemsResponse implements Serializable {
    InventoryItem inventoryItem;
    List<InventoryItem> inventoryList;
}
