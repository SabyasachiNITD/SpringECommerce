package com.myecommerrce.productcatalogserviceproxy.clients.fakestore.client;

import com.myecommerrce.productcatalogserviceproxy.clients.fakestore.dtos.FakeStoreProductDto;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.web.client.RestTemplate;

public class FakeStoreApiClient {
    RestTemplateBuilder restTemplateBuilder;
    public FakeStoreApiClient(RestTemplateBuilder restTemplateBuilder){
        this.restTemplateBuilder = restTemplateBuilder;
    }

    public FakeStoreProductDto getProduct(Long productId){

        RestTemplate restTemplate = restTemplateBuilder.build();
        // getForEntity also provides a ResposeEntity ---- we just get the body of the Entity
        FakeStoreProductDto fakeStoreProductDtoproductDto = restTemplate.getForEntity("https://fakestoreapi.com/products/{id}", FakeStoreProductDto.class, productId).getBody();
        return fakeStoreProductDtoproductDto;
    }
}
