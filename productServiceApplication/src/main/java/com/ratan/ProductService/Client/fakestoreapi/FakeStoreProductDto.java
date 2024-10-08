package com.ratan.ProductService.Client.fakestoreapi;

import com.ratan.ProductService.Dtos.RatingDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FakeStoreProductDto {
    private Long id;
    private String title;
    private String description;
    private double price;
    private String image;
    private String category;
    private RatingDto ratingDto;
}
