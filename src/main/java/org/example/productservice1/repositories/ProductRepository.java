package org.example.productservice1.repositories;

import org.example.productservice1.models.Category;
import org.example.productservice1.models.Product;
import org.example.productservice1.projections.productProjection;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Product findByTitle(String title);

    Optional<Product> findById(Long id);

    List<Product> findAllByCategory_Title(String categoryTitle);

    @Override
    List<Product> findAll();

    Product save(Product product);

    @Query("select p.title as title, p.id as id from Product p where p.category.title = :categoryTitle")
    List<productProjection> getTitleAndIdOfAllProductsByCategoryName(@Param("categoryTitle") String categoryTitle);

}
