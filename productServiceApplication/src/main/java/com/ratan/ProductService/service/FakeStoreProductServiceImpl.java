package com.ratan.ProductService.service;

import com.ratan.ProductService.Dtos.FakeStoreProductDto;
import com.ratan.ProductService.Dtos.ProductDto;
import com.ratan.ProductService.exceptions.NotFoundException;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
//import java.util.Optional;

@Service
public class FakeStoreProductServiceImpl implements ProductService {


    @Autowired
   private ProductRepository productRepository;

//    public Product laaoProducts(){
//
//         productRepository.laaoProductsWithId();
//    }


    private Product convertFakeStoreProductDtoToProduct(FakeStoreProductDto productDto) {
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

        public List<Product>getAllProducts () {
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<FakeStoreProductDto[]> l = restTemplate.getForEntity("https://fakestoreapi.com/products", FakeStoreProductDto[].class);
            List<Product> answer = new ArrayList<>();
            for (FakeStoreProductDto productDto : l.getBody()) {
                answer.add(convertFakeStoreProductDtoToProduct(productDto));
            }
            return answer;
        }

    @Override
    public List<Product> getSingleProducts() {
        return List.of();
    }

    @Override
    public Optional<Product> getSingleProduct() throws NotFoundException {
        return Optional.empty();
    }

    public Product addNewProduct (ProductDto product){
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<FakeStoreProductDto> response = restTemplate.postForEntity(
                    "https://fakestoreapi.com/products", product, FakeStoreProductDto.class
            );

            FakeStoreProductDto productDto = response.getBody();
            return convertFakeStoreProductDtoToProduct(productDto);

        }
        public Optional<Product> getSingleProduct (Long productId){
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<FakeStoreProductDto> response = restTemplate.getForEntity(
                    "https://fakestoreapi.com/products/1", FakeStoreProductDto.class, productId);
            FakeStoreProductDto productDto = response.getBody();
            return Optional.of(convertFakeStoreProductDtoToProduct(productDto));
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
    }

    @Override
    public Product getAllProducts(Long id) {
        return null;
    }
    // public Product replaceProduct (Long productId, Product product){


}

