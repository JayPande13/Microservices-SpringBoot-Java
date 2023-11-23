package com.example.demo.controller;

import com.example.demo.MicroservicesConstants;
import com.example.demo.services.dto.BaseResponseDto;
import com.example.demo.services.dto.ProductCategoryDto;
import com.example.demo.entities.ProductCategory;
import com.example.demo.repositories.ProductCategoryRepository;
import com.example.demo.services.ProductCategoryService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
    @RequestMapping(MicroservicesConstants.BASE_URL + "/category")
@Slf4j
@CrossOrigin
public class ProductCategoryController {
    @Autowired
    ProductCategoryService productCategoryService;

    @Autowired
    ProductCategoryRepository productCategoryRepository;

    @Autowired
    ModelMapper modelMapper;


    @PostMapping("/create")
    public ResponseEntity<BaseResponseDto<ProductCategoryDto>> createUser(@RequestBody ProductCategoryDto productDto) throws Exception {
        log.info("creating product started in Controller");
            ProductCategoryDto responseDto = this.productCategoryService.createProductCategory(productDto);
            if (responseDto == null) {
                return new ResponseEntity<>(
                        BaseResponseDto.<ProductCategoryDto>builder().message("Product Not Created")
                                .statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value()).content(null).build(), HttpStatus.INTERNAL_SERVER_ERROR);
            }else {
                return new ResponseEntity<>(
                        BaseResponseDto.<ProductCategoryDto>builder().message(HttpStatus.OK.toString())
                                .statusCode(HttpStatus.CREATED.value()).content(responseDto).build(), HttpStatus.OK);
            }
    }

    @GetMapping()
    public ResponseEntity<BaseResponseDto<List<ProductCategoryDto>>> getAll(){
        List<ProductCategoryDto> productCategoriesDto;
        productCategoriesDto = this.productCategoryRepository.findAll().stream()
                .map(productCategory -> modelMapper.map(productCategory,ProductCategoryDto.class)).collect(Collectors.toList());

        return new ResponseEntity<>(
                BaseResponseDto.<List<ProductCategoryDto>>builder().message(HttpStatus.OK.toString())
                        .statusCode(HttpStatus.CREATED.value()).content(productCategoriesDto).build(), HttpStatus.OK);

    }
}
