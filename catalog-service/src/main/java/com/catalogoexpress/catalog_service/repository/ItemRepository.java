package com.catalogoexpress.catalog_service.repository;

import org.springframework.stereotype.Repository;


import com.catalogoexpress.catalog_service.models.Item;
import org.springframework.data.jpa.repository.JpaRepository;


@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
}