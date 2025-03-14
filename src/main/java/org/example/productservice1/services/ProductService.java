package org.example.productservice1.services;

//import org.example.productservice1.dtos.CreateProductRequestDto;
import org.example.productservice1.exceptions.ProductNotFoundException;
import org.example.productservice1.models.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.*;

public interface ProductService {
     Product getSingleProduct(long id) throws ProductNotFoundException;
     List<Product> getAllProducts();
     Product createProduct(String title,
                           String description,
                           String imageUrl,
                           double price,
                           String category
                           );
     List<Product> getProductsByCategory(String category) throws ProductNotFoundException;

     Page<Product> findAllProductsPaginated(int pageNo, int pageSize);
}
