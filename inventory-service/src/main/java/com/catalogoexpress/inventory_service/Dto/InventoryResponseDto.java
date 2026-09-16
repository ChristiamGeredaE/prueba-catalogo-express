package com.catalogoexpress.inventory_service.Dto;

import com.catalogoexpress.inventory_service.enums.Estado;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class InventoryResponseDto {
    private Long itemId;
    private Integer stock;
    private Estado estado;
}
