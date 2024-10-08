package com.ratan.ProductService.service;

import com.ratan.ProductService.Client.fakestoreapi.FakeStoreClient;
import com.ratan.ProductService.Client.fakestoreapi.FakeStoreProductDto;
import com.ratan.ProductService.Dtos.ProductDto;
import com.ratan.ProductService.models.Catagory;
import com.ratan.ProductService.models.Product;
import com.ratan.ProductService.repository.ProductRepository;
import jakarta.annotation.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.*;
//import java.util.Optional;

@Service
public class FakeStoreProductServiceImpl implements ProductService {

    @Autowired
   private FakeStoreClient fakeStoreClient;
   @Autowired
   private ProductRepository productRepository;

    private Map<Long,Object>fakeStoreProducts = new HashMap<>();

//    public Product laaoProducts(){
//
//         productRepository.laaoProductsWithId();
//    }

    public Product convertFakeStoreProductDtoToProduct(FakeStoreProductDto productDto) {
        Product product = new Product();
        product.setId(productDto.getId());
        product.setTitle((productDto.getTitle()));
        product.setPrice(productDto.getPrice());
        product.setImagUrl(productDto.getImage());
        Catagory catagory = new Catagory();
        catagory.setName(productDto.getCategory());
        product.setCatagory(catagory);
        return product;

    }


    private <T> ResponseEntity<T> requestForEntity(HttpMethod httpMethod,String url,
                                                   @Nullable Object request,
                                                   Class<T> respponseType, Object... uriVariable) throws RestClientException {

         RestTemplate restTemplate = new RestTemplate();
         RequestCallback requestCallback = restTemplate.httpEntityCallback(request,respponseType);
         ResponseExtractor<ResponseEntity<T>>responseExtractor = restTemplate.responseEntityExtractor(respponseType);

        return restTemplate.execute(url,httpMethod,requestCallback,responseExtractor,uriVariable);
    }

        public List<Product> getAllProducts () {

            List<FakeStoreProductDto>fakeStoreProductDtos = fakeStoreClient.getAllProducts();
            List<Product>answer = new ArrayList<>();
            for(FakeStoreProductDto productDto : fakeStoreProductDtos) {
                answer.add(convertFakeStoreProductDtoToProduct(productDto));
            }
            return answer;
        }

       public Product addNewProduct (ProductDto product){
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<FakeStoreProductDto> response = restTemplate.postForEntity(
                    "https://fakestoreapi.com/products", product, FakeStoreProductDto.class
            );

            FakeStoreProductDto productDto = response.getBody();
            return convertFakeStoreProductDtoToProduct(productDto);

        }



    public Optional<Product> getSingleProduct(Long productId) {
            return fakeStoreClient.getSingleProduct(productId);
        }
        public Product updateProduct (Long productId, Product product){
            RestTemplate restTemplate = new RestTemplate();
            FakeStoreProductDto fakeStoreProductDto = new FakeStoreProductDto();
            fakeStoreProductDto.setDescription(product.getDescription());
            fakeStoreProductDto.setImage(product.getImagUrl());
            fakeStoreProductDto.setTitle(product.getTitle());
            fakeStoreProductDto.setPrice(product.getPrice());
            fakeStoreProductDto.setCategory(product.getCatagory().getName());

            ResponseEntity<FakeStoreProductDto> fakeStoreProductDtoResponseEntity = requestForEntity(
                    HttpMethod.PATCH, "https://fakestoreapi.com/products/7", fakeStoreProductDto,
                    FakeStoreProductDto.class,
                    productId
            );
            return convertFakeStoreProductDtoToProduct(fakeStoreProductDtoResponseEntity.getBody());

        }

    @Override
    public Product replaceProduct(Long productId) {
        return null;
    }

    @Override
    public boolean deleteProduct(Long productId) {
        return false;
    // public Product replaceProduct (Long productId, Product product){


}}

