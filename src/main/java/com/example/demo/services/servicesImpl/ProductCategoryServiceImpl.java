package com.example.demo.services.servicesImpl;

import com.example.demo.services.dto.ProductCategoryDto;
import com.example.demo.entities.ProductCategory;
import com.example.demo.repositories.ProductCategoryRepository;
import com.example.demo.services.ProductCategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductCategoryServiceImpl implements ProductCategoryService {

    @Autowired
    ProductCategoryRepository productCategoryRepository;

    @Autowired
    ModelMapper modelMapper;
    @Override
    public ProductCategoryDto createProductCategory(ProductCategoryDto productCategoryDto) throws Exception {
        ProductCategory productCategoryEntity = new ProductCategory();
        try {
            if (productCategoryDto.getCategoryId() != null) {
                Optional<ProductCategory> productCategoryOptional = this.productCategoryRepository.findById(productCategoryDto.getCategoryId());
                if (productCategoryOptional.isEmpty()) {
                    throw new Exception("Category with this category Id Does not exists");
                } else {
                    productCategoryEntity = this.productCategoryRepository.save(modelMapper.map(productCategoryDto, ProductCategory.class));
                }
            }
            else{
                productCategoryEntity = this.productCategoryRepository.save(modelMapper.map(productCategoryDto, ProductCategory.class));
            }
        }catch (Exception ex){
            throw new Exception(ex);
        }
        return this.modelMapper.map(productCategoryEntity,ProductCategoryDto.class);

    }

}
