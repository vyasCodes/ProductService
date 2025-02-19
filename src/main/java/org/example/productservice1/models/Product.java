package org.example.productservice1.models;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Product extends BaseModel{
    private String title;
    private double price;
    private String description;
    @ManyToOne(cascade = {CascadeType.PERSIST})
    private Category category;
    private String image;
}
