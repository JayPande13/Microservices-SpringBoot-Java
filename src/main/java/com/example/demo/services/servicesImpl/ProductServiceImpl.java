package com.example.demo.services.servicesImpl;

import com.example.demo.entities.QProducts;
import com.example.demo.services.dto.ProductDto;
import com.example.demo.entities.Products;
import com.example.demo.repositories.ProductRepository;
import com.example.demo.services.ProductService;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;


@Service

public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ModelMapper modelMapper;



    @Override
    public ProductDto createProduct(ProductDto productDto) throws Exception {
        ProductDto responseDto;
        try {
            Products mappedEntity = modelMapper.map(productDto, Products.class);
            Products products = this.productRepository.save(mappedEntity);
            responseDto = modelMapper.map(products, ProductDto.class);
        }catch (Exception ex){
            throw new Exception("Error While creating product",ex);
        }
        return responseDto;

    }
    @Override
    public Page<ProductDto> search(Predicate filterPredicate, Pageable pageable) throws Exception {
        QProducts products = QProducts.products;
        BooleanBuilder updatedPredicate = new BooleanBuilder(filterPredicate);
        Page<ProductDto> searchResult = productRepository.findAll(updatedPredicate, pageable)
                .map(search -> modelMapper.map(search, ProductDto.class));

        if (searchResult.getContent().isEmpty()) {
           throw new Exception("No Product Found");
        }
        return null;
    }

    @Override
    public ProductDto getById(Integer productId) throws Exception{
        Optional<Products> products =this.productRepository.findById(productId);
        if(Objects.nonNull(products)){
            return modelMapper.map(products,ProductDto.class);
        }else{
            throw new Exception("Product with Id not found");
        }
    }
}
