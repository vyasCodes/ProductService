package org.example.productservice1.controllers;

import org.example.productservice1.dtos.CreateProductRequestDto;
import org.example.productservice1.dtos.ErrorDto;
import org.example.productservice1.exceptions.ProductNotFoundException;
import org.example.productservice1.models.Product;
import org.example.productservice1.repositories.ProductRepository;
import org.example.productservice1.services.FakeStoreProductService;
import org.example.productservice1.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {
    private final ProductRepository productRepository;
    //    @Autowired
    ProductService productService;
    public ProductController(@Qualifier("SelfProdService") ProductService productService, ProductRepository productRepository) {
        this.productService = productService;
        this.productRepository = productRepository;
    }

    @GetMapping("/products")
    public List<Product> getAllProducts() {
//        System.out.println(productService.getAllProducts());
//        return null;
        return productService.getAllProducts();
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<Product> getSingleProduct(@PathVariable("id") Long id) throws ProductNotFoundException {
        Product p = productService.getSingleProduct(id);

        ResponseEntity<Product> res;

//        if (p == null) {
////            System.out.println("not found");
//            res = new ResponseEntity<>(p, HttpStatus.NOT_FOUND);
//        } else {
//            res = new ResponseEntity<>(p, HttpStatus.OK);
//        }

        res = new ResponseEntity<>(p, HttpStatus.OK);

        return res;

    }

    @PostMapping("/products")
    public Product createProduct(@RequestBody CreateProductRequestDto createProductRequestDto) {
        return productService.createProduct(createProductRequestDto.getTitle(),
                createProductRequestDto.getDescription(),
                createProductRequestDto.getImageUrl(),
                createProductRequestDto.getPrice(),
                createProductRequestDto.getCategory());
    }

    @GetMapping("/products/category/{category}")
    public List<Product> getProductByCategory(@PathVariable("category") String category) {
        return productService.getProductsByCategory(category);
    }

}
