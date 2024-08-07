package com.ratan.ProductService.Client.authenticationClient;

import com.ratan.ProductService.Client.authenticationClient.dtos.ValidateTokenRequestDto;
import com.ratan.ProductService.Client.authenticationClient.dtos.ValidateTokenResponseDto;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class AuthenticationClient {

    private RestTemplateBuilder restTemplateBuilder;

    public AuthenticationClient(RestTemplateBuilder restTemplateBuilder){
        this.restTemplateBuilder=restTemplateBuilder;
    }

    public Boolean validate(String token, Long userId){
        RestTemplate restTemplate = restTemplateBuilder.build();
//        ValidateTokenRequestDto request = new ValidateTokenRequestDto();
//        request.setToken(token);
//        request.setUserId(userId);
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization",token);
        HttpEntity entity = new HttpEntity(headers);
        ResponseEntity<Boolean> response = restTemplate.exchange("http://localhost:8080/Auth/validate", HttpMethod.GET, entity, Boolean.class);
//        ResponseEntity<ValidateTokenResponseDto>responses = restTemplate.getForEntity()
//                "http://localhost:8080/Auth/validate",
//                    request,
//                    ValidateTokenResponseDto.class);
//
     return response.getBody();
    }

}
