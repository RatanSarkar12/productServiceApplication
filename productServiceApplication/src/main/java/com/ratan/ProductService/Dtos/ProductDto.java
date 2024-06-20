package com.ratan.ProductService.Dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductDto {

     private Long id;
     private String title;
     private String description;
     private int pricce;
     private String image;
     private String category;
     private String rating;

}
