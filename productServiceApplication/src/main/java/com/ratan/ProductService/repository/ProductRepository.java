package com.ratan.ProductService.repository;

import com.ratan.ProductService.Dtos.ProductDto;
import com.ratan.ProductService.exceptions.NotFoundException;
import com.ratan.ProductService.models.Product;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

        Product save(Product product);

        Product findProductById(Long id);

      //  Product findProductByIds(Long id);







}