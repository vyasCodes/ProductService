package com.example.productservice.dtos;


import com.example.productservice.model.Category;
import com.example.productservice.model.Product;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FakeStoreProductDto {
    private String title;
    private String description;
    private String image;
    private String category;
    private double price;

    public Product toProduct() {
        Product product = new Product();
        product.setTitle(title);
        product.setDescription(description);
        Category category = new Category();
        category.setTitle(this.category);
        product.setCategory(category);
        product.setPrice(price);
        product.setImageUrl(image);
        return product;
    }

}
