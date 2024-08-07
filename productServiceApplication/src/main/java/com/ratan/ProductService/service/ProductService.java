package com.ratan.ProductService.service;

import com.ratan.ProductService.Dtos.ProductDto;
import com.ratan.ProductService.Client.fakestoreapi.FakeStoreProductDto;
import com.ratan.ProductService.models.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    Product addNewProduct(ProductDto productDto);

    Optional<Product> getSingleProduct(Long productId);

    Product updateProduct(Long productId, Product product);

     Product replaceProduct(Long productId);


     boolean deleteProduct(Long productId);

     List<Product> getAllProducts();
}
