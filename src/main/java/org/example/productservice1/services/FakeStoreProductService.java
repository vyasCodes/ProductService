package org.example.productservice1.services;

//import org.example.productservice1.dtos.CreateProductRequestDto;
import org.example.productservice1.dtos.FakeStoreProductDto;
import org.example.productservice1.exceptions.ProductNotFoundException;
import org.example.productservice1.models.Product;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service("FakeStore")
public class FakeStoreProductService implements ProductService {
    RestTemplate restTemplate;
    public FakeStoreProductService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
    @Override
    public Product getSingleProduct(long id) throws ProductNotFoundException {

        ResponseEntity<FakeStoreProductDto> fakeStoreProductDto = restTemplate.getForEntity("https://fakestoreapi.com/products/" + id,
                FakeStoreProductDto.class);

        FakeStoreProductDto fakeStoreProductDtoResponse = fakeStoreProductDto.getBody();

        if (fakeStoreProductDtoResponse == null) {
            throw new ProductNotFoundException("Product with id " + id + " not found.Check product id.");
        }

        return fakeStoreProductDtoResponse.toProduct(fakeStoreProductDtoResponse);
    }

    @Override
    public List<Product> getAllProducts() {
        FakeStoreProductDto[] fakeStoreProductDtos = restTemplate.getForObject("https://fakestoreapi.com/products",
                FakeStoreProductDto[].class);
        List<Product> products = new ArrayList<>();
        for (FakeStoreProductDto fakeStoreProductDto : fakeStoreProductDtos) {
            products.add(fakeStoreProductDto.toProduct(fakeStoreProductDto));
        }
//        if (products.isEmpty()) return null;
        return products;
    }

    @Override
    public Product createProduct(String title, String description, String imageUrl, double price, String category) {
        FakeStoreProductDto fakeStoreProductDto = new FakeStoreProductDto();
        fakeStoreProductDto.setTitle(title);
        fakeStoreProductDto.setDescription(description);
        fakeStoreProductDto.setImage(imageUrl);
        fakeStoreProductDto.setPrice(price);
        fakeStoreProductDto.setCategory(category);

        fakeStoreProductDto = restTemplate.postForObject("https://fakestoreapi.com/products", fakeStoreProductDto, FakeStoreProductDto.class);
        return fakeStoreProductDto.toProduct(fakeStoreProductDto);
    }

    @Override
    public List<Product> getProductsByCategory(String category) {
        return null;
    }
}
