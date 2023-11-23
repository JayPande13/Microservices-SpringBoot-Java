package com.example.demo.services.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProductCategoryDto {
    Integer categoryId;
    String categoryName;
    String categoryDesc;
    List<ProductDto> products;
}
