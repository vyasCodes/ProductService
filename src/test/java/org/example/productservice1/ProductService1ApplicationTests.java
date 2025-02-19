package org.example.productservice1;

import org.example.productservice1.models.Product;
import org.example.productservice1.projections.productProjection;
import org.example.productservice1.repositories.ProductRepository;
import org.example.productservice1.services.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.*;

@SpringBootTest
class ProductService1ApplicationTests {
    @Autowired
    ProductRepository productRepository;

    @Test
    void contextLoads() {
    }

    @Test
    void testingQueries() {
//        List<Product> products = productRepository.findAllByCategory_Title("Electronics");
//        System.out.println(products);

        List<productProjection> products = productRepository.getTitleAndIdOfAllProductsByCategoryName("Furniture");
        for (productProjection product : products) {
            System.out.println(product.getTitle());
            System.out.println(product.getId());

        }

    }

}
