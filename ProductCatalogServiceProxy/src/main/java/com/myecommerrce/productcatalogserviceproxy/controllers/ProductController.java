package com.myecommerrce.productcatalogserviceproxy.controllers;

import com.myecommerrce.productcatalogserviceproxy.dtos.ProductDto;
import org.springframework.web.bind.annotation.*;

@RestController
public class ProductController {
    @GetMapping("/products")
    public String getProducts(){
        return "Returning list of all the products" ;
    }

    @GetMapping("/products/{id}")
    public String getProduct(@PathVariable("id") String productId){
        return "Return the product with id: " + productId ;
    }

    @PostMapping("/products")
    public String createProduct(@RequestBody ProductDto productDto){
        return "Creating Product : " + productDto ;
    }
}