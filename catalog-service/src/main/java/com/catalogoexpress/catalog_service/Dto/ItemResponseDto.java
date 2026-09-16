package com.catalogoexpress.catalog_service.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemResponseDto {
    private Long id;
    private String title;
    private String description;
    private String imageUrl;
    private double price;
    private double rating;
    private int stock;
    private String status;
    private double score;


}
