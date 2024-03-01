package com.myecommerrce.productcatalogserviceproxy.services;

import com.myecommerrce.productcatalogserviceproxy.clients.fakestore.dtos.FakeStoreProductDto;
import com.myecommerrce.productcatalogserviceproxy.dtos.ProductDto;
import com.myecommerrce.productcatalogserviceproxy.models.Category;
import com.myecommerrce.productcatalogserviceproxy.models.Product;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class FakeStoreProductService implements IProductService {
    private RestTemplateBuilder restTemplateBuilder;

    public FakeStoreProductService(RestTemplateBuilder restTemplateBuilder){
        this.restTemplateBuilder = restTemplateBuilder;
    }

    @Override
    public List<Product> getProducts(){
        RestTemplate restTemplate = restTemplateBuilder.build();
        FakeStoreProductDto[] fakeProductDtos = restTemplate.getForEntity("https://fakestoreapi.com/products", FakeStoreProductDto[].class).getBody();
        List<Product> products = new ArrayList<>();
        for(FakeStoreProductDto fakeProductDto : fakeProductDtos){
            Product product = getProductFromFakeProductDto(fakeProductDto);
            products.add(product);
        }
        return products;
    }
    @Override
    public Product getProduct(Long productId){

        RestTemplate restTemplate = restTemplateBuilder.build();
        // getForEntity also provides a ResposeEntity ---- we just get the body of the Entity
        FakeStoreProductDto fakeStoreProductDtoproductDto = restTemplate.getForEntity("https://fakestoreapi.com/products/{id}", FakeStoreProductDto.class, productId).getBody();
        return getProductFromFakeProductDto(fakeStoreProductDtoproductDto);
    }
    @Override
    public Product createProduct(Product product){
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto> responseEntity = restTemplate.postForEntity("http://fakestoreapi.com/products", product,FakeStoreProductDto.class);
        return getProductFromFakeProductDto(responseEntity.getBody());
    }
    @Override
    public Product updateProduct(Long id,Product product){
        RestTemplate restTemplate = restTemplateBuilder.build();
        FakeStoreProductDto fakeStoreProductDto = restTemplate.patchForObject("https://fakestoreapi.com/{id}", product, FakeStoreProductDto.class,id);
        Product resultantProduct = getProductFromFakeProductDto(fakeStoreProductDto);
        return resultantProduct;
    }

    private Product getProductFromFakeProductDto(FakeStoreProductDto productDto){
        Product product = new Product();
        product.setTitle(productDto.getTitle());
        product.setDescription(productDto.getDescription());
        product.setPrice(productDto.getPrice());
        product.setImageUrl(productDto.getImage());
//        Category category = new Category();
//        category.setName(productDto.getCategory());
//        product.setCategory(category);
        product.setId(productDto.getId());
        return product;
    }
}
