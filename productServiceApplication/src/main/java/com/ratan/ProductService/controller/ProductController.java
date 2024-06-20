package com.ratan.ProductService.controller;

import com.ratan.ProductService.Dtos.ProductDto;
import com.ratan.ProductService.exceptions.NotFoundException;
import com.ratan.ProductService.models.Catagory;
import com.ratan.ProductService.models.Product;
import com.ratan.ProductService.repository.ProductRepository;
import com.ratan.ProductService.service.ProductService;
import jakarta.annotation.Nullable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.rmi.NotBoundException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products")
public class ProductController {

    //private IProductService productService;
    private ProductRepository productRepository;
    private ProductService productService;
    public ProductController(ProductService productService, ProductRepository productRepository){
        this.productRepository=productRepository;
        this.productService = productService;

    }
    @GetMapping()
    public ResponseEntity<List<Product>> getAllProducts(@Nullable @RequestHeader("USER_ID") Long userid){

           // List<Product>products = productService.getAllProducts(userid);

            //return  new ResponseEntity<>(products, HttpStatus.OK);
        return null;
    }

    @GetMapping("/productId")
    public ResponseEntity<Product> getSingleProduct(Long productId) throws NotFoundException, NotBoundException {
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();

        headers.add(
                "auth_token", "noaccess4uBro"
        );

        Optional<Product> productOptional = productService.getSingleProduct(productId);

        if (productOptional.isEmpty()) {
            throw new NotFoundException("no product with product id " + productId);
        }
        ResponseEntity<Product>response = new ResponseEntity(productService.getSingleProduct(productId), headers, HttpStatus.NOT_FOUND);
        return response;
    }
    @PostMapping("/products")
    public ResponseEntity<Product> addNewProduct(@RequestBody ProductDto products){
        Product newProduct = new Product();
        newProduct.setDescription(products.getDescription());
        newProduct.setTitle(products.getTitle());
        newProduct.setPrice(products.getPricce());
        newProduct.setImagUrl(products.getImage());
        newProduct = productRepository.save(newProduct);
        ResponseEntity<Product>response = new ResponseEntity<>(newProduct,HttpStatus.OK);
        return response;
    }

    @PatchMapping("/{productId}")
    public Product updateProduct(@PathVariable("productId") Long productId,
                                @RequestBody ProductDto productDto){
        Product product = new Product();
        product.setId(productDto.getId());
        product.setTitle(productDto.getTitle());
        product.setCatagory(new Catagory());
        product.getCatagory().setName(productDto.getCategory());
        product.setDescription(productDto.getDescription());
        product.setPrice(productDto.getPricce());
        return productService.updateProduct(productId,product);

    }
    @DeleteMapping("/{productId}")
    public String deleteProduct(@PathVariable("productId") Long productId){
        return "deletingn a product "+productId ;
    }

//
//     @GetMapping("/productWithId")
//     public Product laapProductsWithId(Long id){
//
//       return  productRepository.laaoProductsWithId(103l);
//
//         return
//     }
}
