package com.ratan.ProductService.controller;

import com.ratan.ProductService.Client.authenticationClient.AuthenticationClient;
import com.ratan.ProductService.Client.authenticationClient.dtos.SessionStatus;
import com.ratan.ProductService.Client.authenticationClient.dtos.ValidateTokenResponseDto;
import com.ratan.ProductService.Dtos.ProductDto;
import com.ratan.ProductService.exceptions.NotFoundException;
import com.ratan.ProductService.Client.fakestoreapi.FakeStoreProductDto;
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

import javax.management.relation.Role;
import java.rmi.NotBoundException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products")
public class ProductController {
    private AuthenticationClient authenticationClient;
    //private IProductService productService;
    private ProductRepository productRepository;

    private ProductService productService;

    public ProductController(ProductService productService, AuthenticationClient authenticationClient, ProductRepository productRepository){
        this.productRepository=productRepository;
        this.productService = productService;
        this.authenticationClient=authenticationClient;
    }
    @GetMapping("/getAll")
    public ResponseEntity<List<Product>> getAllProducts(@Nullable @RequestHeader("AUTH-TOKEN") String token,
                                                        @Nullable @RequestHeader("USER-ID") Long userId) {

        //check if token exist
//        if(token==null || userId==null){
//            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
//        }
//        Boolean response = authenticationClient.validate(token,userId);
//        //check if tokrn is valid
//        if(response == false){
//            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
//        }
        //validate token
//        RestTemplate rt = new RestTemplate();
//        rt.get("localhost:9090/auth/Validate?")


        //check user has permission or not
//        Boolean isUserAdmin=false;
//        for(Role role:response.getUserDto().getRoles()){
//            if(role.getRoleName().equals("ADMIN")){
//                isUserAdmin=true;
//            }
//        }
//        if(!isUserAdmin){
//            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
//        }
        List<Product>products = (List<Product>) productService.getAllProducts();
        return new ResponseEntity<>(products,HttpStatus.OK);
    }

    @GetMapping("/{productId}")
    public ResponseEntity<Product> getSingleProduct(@PathVariable ("productId") Long productId) throws NotFoundException, NotBoundException {
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();

//        headers.add(
//                "auth_token", "noaccess"
//        );

        Optional<Product> productOptional = productService.getSingleProduct(productId);

        if (productOptional.isEmpty()) {
            throw new NotFoundException("no product with product id " + productId);
        }
        ResponseEntity<Product>response = new ResponseEntity(productService.getSingleProduct(productId), HttpStatus.NOT_FOUND);
        return response;
    }
    @PostMapping("/product")
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
//     @GetMapping("/productWithId")
//     public Product laapProductsWithId(Long id){
//
//       return  productRepository.laaoProductsWithId(103l);
//
//         return
//     }
}
