package org.example.productservice1.services;

import org.example.productservice1.exceptions.ProductNotFoundException;
import org.example.productservice1.models.Category;
import org.example.productservice1.models.Product;
import org.example.productservice1.repositories.CategoryRepository;
import org.example.productservice1.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("SelfProdService")
public class SelfProductService implements ProductService {
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    ProductRepository productRepository;

//    public SelfProductService(CategoryRepository categoryRepository,
//                              ProductRepository productRepository) {
//        this.categoryRepository = categoryRepository;
//        this.productRepository = productRepository;
//    }
    @Override
    public Product getSingleProduct(long id) throws ProductNotFoundException {
        Optional<Product> p = productRepository.findById(id);

        if (p.isEmpty()) throw new ProductNotFoundException("Product " + id + " not found.Invalid Id.");
        return p.get();
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product createProduct(String title,
                                 String description,
                                 String imageUrl,
                                 double price,
                                 String category) {

        Product product = new Product();
        product.setTitle(title);
        product.setDescription(description);
        product.setImage(imageUrl);
        product.setPrice(price);
        Category categoryObj = new Category();
        categoryObj.setTitle(category);
        product.setCategory(categoryObj);

        Category dbCatObj = categoryRepository.findByTitle(category);

        if (dbCatObj == null) {
            Category newCat = new Category();
            newCat.setTitle(category);
            dbCatObj = newCat;
        }

        product.setCategory(dbCatObj);
        Product p = productRepository.save(product);

        return p;
    }

    @Override
    public List<Product> getProductsByCategory(String category) throws ProductNotFoundException {
        List<Product> p = productRepository.findAllByCategory_Title(category);

        if (p.size() == 0) throw new ProductNotFoundException("No products with category: " + category + " found.");

        return p;
    }

    @Override
    public Page<Product> findAllProductsPaginated(int pageNo, int size) {
        Page<Product> paginatedProducts = productRepository.findAll(PageRequest.of(pageNo,
                size,
                Sort.by("id").descending()));
        return paginatedProducts;
    }
}
