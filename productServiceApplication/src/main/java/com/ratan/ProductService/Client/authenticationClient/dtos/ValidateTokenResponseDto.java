package com.ratan.ProductService.Client.authenticationClient.dtos;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.support.SessionStatus;

@Getter
@Setter
public class ValidateTokenResponseDto {
    private UserDto userDto;

    private SessionStatus sessionStatus;

}
