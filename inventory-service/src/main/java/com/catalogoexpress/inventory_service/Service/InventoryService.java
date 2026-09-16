package com.catalogoexpress.inventory_service.Service;

import com.catalogoexpress.inventory_service.Dto.InventoryResponseDto;
import com.catalogoexpress.inventory_service.enums.Estado;
import com.catalogoexpress.inventory_service.models.Inventory;
import com.catalogoexpress.inventory_service.repository.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class InventoryService {
    @Autowired
    private InventoryRepository inventoryRepository;

    public InventoryResponseDto getByItemId(Long itemId) {
        Optional<Inventory> inventoryOpt = inventoryRepository.findById(itemId);
        if (inventoryOpt.isPresent()) {
            Inventory inv = inventoryOpt.get();
            return new InventoryResponseDto(inv.getItemId(), inv.getStock(), inv.getEstado());
        }

        return new InventoryResponseDto(itemId, 0, Estado.PENDIENTE);
    }
}

