package com.example.demo.services;

import com.example.demo.services.dto.ProductDto;
import com.querydsl.core.types.Predicate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface ProductService {

    public ProductDto createProduct(ProductDto productDto) throws Exception;

    public Page<ProductDto> search(Predicate filterPredicate, Pageable pageable) throws Exception;
    public ProductDto getById(Integer productId) throws Exception;
}
