package org.example.productservice1.dtos;


import lombok.Getter;
import lombok.Setter;
import org.example.productservice1.models.*;

@Getter
@Setter
public class FakeStoreProductDto {
    private String title;
    private String description;
    private double price;
    private String image;
    private String category;

    public Product toProduct(FakeStoreProductDto fakeStoreProductDto) {
        Product product = new Product();
        product.setDescription(description);
        product.setTitle(title);
        product.setPrice(price);
        product.setImage(image);
        Category category = new Category();
        category.setTitle(fakeStoreProductDto.getCategory());
        product.setCategory(category);

        return product;
    }

}
