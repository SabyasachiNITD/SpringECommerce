package com.myecommerrce.productcatalogserviceproxy.clients.fakestore.client;

import com.myecommerrce.productcatalogserviceproxy.clients.fakestore.dtos.FakeStoreProductDto;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class FakeStoreApiClient {
    RestTemplateBuilder restTemplateBuilder;
    public FakeStoreApiClient(RestTemplateBuilder restTemplateBuilder){
        this.restTemplateBuilder = restTemplateBuilder;
    }

    public FakeStoreProductDto getProduct(Long productId){
        // getForEntity also provides a ResposeEntity ---- we just get the body of the Entity
        RestTemplate restTemplate = restTemplateBuilder.build();
        FakeStoreProductDto fakeStoreProductDto = restTemplate.getForEntity("https://fakestoreapi.com/products/{id}", FakeStoreProductDto.class, productId).getBody();
        return fakeStoreProductDto;
    }
    public FakeStoreProductDto[] getProducts(){
        RestTemplate restTemplate = restTemplateBuilder.build();
        FakeStoreProductDto[] fakeStoreProductDtos = restTemplate.getForEntity("https://fakestoreapi.com/products", FakeStoreProductDto[].class).getBody();
        return fakeStoreProductDtos;
    }

    public FakeStoreProductDto createProduct(FakeStoreProductDto fakeStoreProductDto){
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto> fakeStoreResponseEntity = restTemplate.postForEntity("http://fakestoreapi.com/products", fakeStoreProductDto,FakeStoreProductDto.class);
        return fakeStoreResponseEntity.getBody();
    }

}
