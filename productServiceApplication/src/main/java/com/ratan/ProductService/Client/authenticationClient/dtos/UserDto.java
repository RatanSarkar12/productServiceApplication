package com.ratan.ProductService.Client.authenticationClient.dtos;

import lombok.Getter;
import lombok.Setter;

import javax.management.relation.Role;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class UserDto {
    private String email;
    private Set<Role>roles = new HashSet<>();
}
