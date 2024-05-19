package com.jamith.tlms.dto.request.inventoryService;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class GetItemsRequest implements Serializable {
    Integer itemId;
    List<Integer> itemIds;
}
