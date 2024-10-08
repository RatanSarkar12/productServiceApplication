package com.ratan.ProductService.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Product extends BaseModel{
    private String title;
    @ManyToOne
    private Catagory catagory;
    private String imagUrl;
    private String description;
    private double price;
}
