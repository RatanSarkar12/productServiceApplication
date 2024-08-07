package com.ratan.ProductService.Client.fakestoreapi;

import com.ratan.ProductService.models.Catagory;
import com.ratan.ProductService.models.Product;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
@Component
public class FakeStoreClient {
    private RestTemplateBuilder restTemplateBuilder;
    private RedisTemplate redisTemplate;

    public FakeStoreClient(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplateBuilder = restTemplateBuilder;
    }

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

    public List<FakeStoreProductDto> getAllProducts() {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<FakeStoreProductDto[]> l = restTemplate.getForEntity(
                "https://fakestoreapi.com/products", FakeStoreProductDto[].class);

        return Arrays.asList(l.getBody());
    }

    public Optional<Product> getSingleProduct(Long productId) {
       // FakeStoreProductDto fakeStoreProductDto = (FakeStoreProductDto) redisTemplate.opsForHash().get(productId, "PRODUCTS");
        FakeStoreProductDto fakeStoreProductDto = null;
        if (fakeStoreProductDto != null) {
            return Optional.of(convertFakeStoreProductDtoToProduct(fakeStoreProductDto));
        }
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto> response = restTemplate.getForEntity(
                "https://fakestoreapi.com/products/{id}",
                FakeStoreProductDto.class, productId);

        FakeStoreProductDto productDto = response.getBody();
//        fakeStoreProducts.put(productId, productDto);

        //redisTemplate.opsForHash().put(productId, "PRODUCTS", productDto);
        if (productDto == null) {
            return Optional.empty();
        }

        return Optional.of(convertFakeStoreProductDtoToProduct(productDto));
    }
}
