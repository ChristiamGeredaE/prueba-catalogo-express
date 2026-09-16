package com.catalogoexpress.catalog_service.Dto;

import lombok.Data;

@Data
public class InventoryResponseDto {
    private Long itemId;
    private int stock;
    private String status;
}
