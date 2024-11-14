package com.example.productservice.exceptions;

import org.springframework.web.bind.annotation.ExceptionHandler;

public class ProductNotFoundException extends Exception {
    public ProductNotFoundException(String message) {
        super(message);
    }
}
