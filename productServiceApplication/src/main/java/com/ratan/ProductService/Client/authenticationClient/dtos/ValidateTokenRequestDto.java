package com.ratan.ProductService.Client.authenticationClient.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter

@Setter
public class ValidateTokenRequestDto {

      private long userId;
      private String token;

      public void setToken(String token) {
      }
}
