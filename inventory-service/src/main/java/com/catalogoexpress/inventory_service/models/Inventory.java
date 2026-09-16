package com.catalogoexpress.inventory_service.models;

import com.catalogoexpress.inventory_service.enums.Estado;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "inventory")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Inventory {
    @Id
    private Long itemId;
    @Column(nullable = false)
    private Integer stock;

    @Column(nullable = false, length = 50)
    private Estado estado;



}
