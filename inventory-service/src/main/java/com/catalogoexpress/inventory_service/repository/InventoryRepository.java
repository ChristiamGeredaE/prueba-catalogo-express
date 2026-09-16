package com.catalogoexpress.inventory_service.repository;

import com.catalogoexpress.inventory_service.models.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory,Long> {
}
