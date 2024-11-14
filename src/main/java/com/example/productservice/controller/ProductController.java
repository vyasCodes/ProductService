package com.example.productservice.controller;

import java.util.List;

import com.example.productservice.dtos.CreateProductRequestDto;
import com.example.productservice.exceptions.ProductNotFoundException;
import com.example.productservice.model.Category;
import com.example.productservice.model.Product;

import com.example.productservice.services.ProductService;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ProductController {

    ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/products")
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<Product> getSingleProduct(@PathVariable("id") Long id) throws ProductNotFoundException {
        Product p = productService.getSingleProduct(id);
        ResponseEntity<Product> productEntity;
        if (p == null) {
            productEntity = new ResponseEntity<>(p, HttpStatus.NOT_FOUND);
        } else {
            productEntity = new ResponseEntity<>(p, HttpStatus.OK);
        }

        return productEntity;
    }

    @PostMapping("/products")
    public Product createProduct(@RequestBody CreateProductRequestDto createProductRequestDto) {
        return productService.createProduct(createProductRequestDto.getTitle(),
                createProductRequestDto.getDescription(),
                createProductRequestDto.getPrice(),
                createProductRequestDto.getImage(),
                createProductRequestDto.getCategory());
    }

    @PutMapping("/products/{id}")
    public Product updateProduct(@PathVariable("id") Long id,
                                 @RequestBody CreateProductRequestDto requestDto) {

        return productService.updateProduct(id,
                requestDto.getTitle(),
                requestDto.getDescription(),
                requestDto.getPrice(),
                requestDto.getImage(),
                requestDto.getCategory());
    }

    @GetMapping("products/categories")
    public List<String> getAllCategories() {
        return productService.getAllCategories();
    }
}
