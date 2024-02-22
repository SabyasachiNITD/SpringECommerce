package com.myecommerrce.productcatalogserviceproxy.services;

import com.myecommerrce.productcatalogserviceproxy.dtos.ProductDto;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

public class ProductService implements IProductService {
    private RestTemplateBuilder restTemplateBuilder;

    public ProductService(RestTemplateBuilder restTemplateBuilder){
        this.restTemplateBuilder = restTemplateBuilder;
    }

    @Override
    public String getProducts(){
        return null ;
    }
    @Override
    public String getProduct(String productId){
        RestTemplate restTemplate = restTemplateBuilder.build();
        ProductDto productDto = restTemplate.getForEntity("https://fakestoreapi.com/products/{id}", ProductDto.class, productId).getBody();
        return productDto.toString();
    }
    @Override
    public String createProduct(ProductDto productDto){
        return null;
    }
    @Override
    public String updateProduct(ProductDto productDto){
        return null;
    }
}
