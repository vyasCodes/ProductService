package org.example.productservice1.exceptions;

public class ProductNotFoundException extends Exception {
    public ProductNotFoundException(String message) {
      super(message);
    }
}
