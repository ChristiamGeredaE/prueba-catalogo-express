package com.catalogoexpress.inventory_service.controller;

import com.catalogoexpress.inventory_service.Dto.InventoryResponseDto;
import com.catalogoexpress.inventory_service.Service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/inventory")
public class InventoryController {
    @Autowired
    private InventoryService inventoryService;

    @GetMapping("/{itemId}")
    public ResponseEntity<InventoryResponseDto> getInventoryByItemId(@PathVariable Long itemId) {
        InventoryResponseDto response = inventoryService.getByItemId(itemId);
        return ResponseEntity.ok(response);
    }
}
