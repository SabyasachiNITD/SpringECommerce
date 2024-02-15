package com.myecommerrce.productcatalogserviceproxy.controlles;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {
    @GetMapping("/products")
    public String getProducts(){
        return "Returning list of all the products" ;
    }

    @GetMapping("/products/{id}")
    public static String getProduct(@PathVariable("id") String productId){
        return "Return the product with id: " + productId ;
    }
}
