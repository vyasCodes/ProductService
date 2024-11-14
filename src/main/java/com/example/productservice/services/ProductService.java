package com.example.productservice.services;

import com.example.productservice.dtos.CreateProductRequestDto;
import com.example.productservice.exceptions.ProductNotFoundException;
import com.example.productservice.model.Product;

import java.util.List;

public interface ProductService {
    List<Product> getAllProducts();
    Product getSingleProduct(Long id) throws ProductNotFoundException;
    Product createProduct(String title,
                          String description,
                          double price,
                          String imageUrl,
                          String category);
    Product updateProduct(
            Long id,
            String title,
            String description,
            double price,
            String imageUrl,
            String category);

    List<String> getAllCategories();
}
