package com.example.vegetableecommerce.dto;

import lombok.Data;

@Data
public class ProductDto {
    private String name;
    private Long price;
    private String description;
    private Integer quantity;
    private String category;
    private Integer discount;
    private String producer;
    private String avatarImage;
    private String updatedBy;
}
