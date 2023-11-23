package com.example.demo.controller;

import com.example.demo.MicroservicesConstants;
import com.example.demo.services.dto.BaseResponseDto;
import com.example.demo.services.dto.PageResponseDto;
import com.example.demo.services.dto.ProductDto;
import com.example.demo.entities.Products;
import com.example.demo.services.ProductService;
import com.querydsl.core.types.Predicate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(MicroservicesConstants.BASE_URL + "/product")
@Slf4j
@CrossOrigin
public class ProductController {

    @Autowired
    ProductService productService;

    @PostMapping("/create")
    public ResponseEntity<BaseResponseDto<ProductDto>> createUser(@RequestBody ProductDto productDto) throws Exception {
        log.info("creating product started in Controller");
        ProductDto responseDto = this.productService.createProduct(productDto);
        if (responseDto == null) {
            return new ResponseEntity<>(
                    BaseResponseDto.<ProductDto>builder().message("Product Not Created")
                            .statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value()).content(null).build(), HttpStatus.INTERNAL_SERVER_ERROR);
        }else {
            return new ResponseEntity<>(
                    BaseResponseDto.<ProductDto>builder().message(HttpStatus.OK.toString())
                            .statusCode(HttpStatus.CREATED.value()).content(responseDto).build(), HttpStatus.OK);
        }
    }
    @GetMapping("/search")
    public PageResponseDto search(@QuerydslPredicate(root = Products.class) Predicate filterPredicate, Pageable pageRequest) throws Exception {

        log.info("creating product started in Controller");
        Page<ProductDto> responseDto = this.productService.search(filterPredicate,pageRequest);
        return PageResponseDto.builder().content(responseDto)
                .message("Search Result Displayed").statusCode(HttpStatus.OK.value()).build();
    }
    @GetMapping("/product/{productId}")
    public ResponseEntity<BaseResponseDto<ProductDto>> getById(@PathVariable Integer productId) throws Exception {

        log.info("creating product started in Controller");
        ProductDto responseDto = this.productService.getById(productId);
          return new ResponseEntity<>(
                    BaseResponseDto.<ProductDto>builder().message(HttpStatus.OK.toString())
                            .statusCode(HttpStatus.CREATED.value()).content(responseDto).build(), HttpStatus.OK);
    }


}
