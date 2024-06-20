package com.ratan.ProductService.service;

import com.ratan.ProductService.Dtos.ProductDto;
import com.ratan.ProductService.exceptions.NotFoundException;
import com.ratan.ProductService.models.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    List<Product>getSingleProducts();

    Optional<Product>getSingleProduct(Long Id) throws NotFoundException;


    Optional<Product> getSingleProduct() throws NotFoundException;

    Product addNewProduct(ProductDto productDto);

     Product updateProduct(Long productId,Product product);

     Product replaceProduct(Long productId);


     boolean deleteProduct(Long productId);
     Product getAllProducts(Long id);
}
