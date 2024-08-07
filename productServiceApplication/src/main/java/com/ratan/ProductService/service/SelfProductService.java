package com.ratan.ProductService.service;

import com.ratan.ProductService.Dtos.ProductDto;
import com.ratan.ProductService.exceptions.NotFoundException;
import com.ratan.ProductService.models.Product;
import com.ratan.ProductService.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service(value = "selfProductService")
public class SelfProductService {

     private ProductRepository productRepository;

     public SelfProductService(ProductRepository productRepository){
         this.productRepository = productRepository;
     }
     public List<Product>getAllProduct(){
          return productRepository.findAll();
    }
    public Optional<Product> getSingleProduct(Long productId)throws NotFoundException{

         Product product = productRepository.findProductById(productId);
//         if(product == null){
//             return "product doesno exist";
//             // new NotFoundException("PRODUCT doesnot exist");
//         }
      return Optional.of(product);
    }
    public Product addNewProduct(ProductDto productDto){
         return null;
    }
    public Product updateProduct(Long productId,Product product){
         return null;
    }
    public Product replaceProduct(Long productId){
          return null;
    }
    public boolean deleteProduct(Long productId)
    {
        return true;
    }

}
