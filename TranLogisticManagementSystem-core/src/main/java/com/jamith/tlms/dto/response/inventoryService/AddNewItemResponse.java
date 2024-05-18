package com.jamith.tlms.dto.response.inventoryService;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class AddNewItemResponse implements Serializable {
    String description;
}
