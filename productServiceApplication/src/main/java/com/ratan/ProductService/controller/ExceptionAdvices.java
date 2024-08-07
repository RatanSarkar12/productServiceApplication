package com.ratan.ProductService.controller;

import com.ratan.ProductService.exceptions.ErrorResponseDto;
import com.ratan.ProductService.exceptions.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionAdvices {

     @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorResponseDto> exceptiponHandler(Exception exception){
         ErrorResponseDto errorResponseDto = new ErrorResponseDto();
         errorResponseDto.setErrorMsg(exception.getMessage());
         return new ResponseEntity<>(errorResponseDto, HttpStatus.NOT_FOUND);
     }
}
