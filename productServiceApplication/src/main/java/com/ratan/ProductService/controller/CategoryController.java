package com.ratan.ProductService.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products/categories")
public class CategoryController {

//    private CategoryService categoryService;
//
//
//    public CategoryController(CategoryService categoryService){
//        this.categoryService = categoryService;
//    }
    @GetMapping("/catagories")
    public String getAllCatagories(){
            return "getting al catagory";
      }
     @GetMapping("/{catagoryId}")
    public String getProductInCatagory(@PathVariable("catagoryId") Long catagortId){
          return "Get products in Catagory";

    }

}
