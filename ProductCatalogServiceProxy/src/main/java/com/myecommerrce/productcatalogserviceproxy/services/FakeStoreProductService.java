package com.myecommerrce.productcatalogserviceproxy.services;

import com.myecommerrce.productcatalogserviceproxy.clients.fakestore.client.FakeStoreApiClient;
import com.myecommerrce.productcatalogserviceproxy.clients.fakestore.dtos.FakeStoreProductDto;
import com.myecommerrce.productcatalogserviceproxy.models.Product;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class FakeStoreProductService implements IProductService {
    private RestTemplateBuilder restTemplateBuilder;
    private FakeStoreApiClient fakeStoreApiClient;
    public FakeStoreProductService(RestTemplateBuilder restTemplateBuilder,FakeStoreApiClient fakeStoreApiClient){
        this.restTemplateBuilder = restTemplateBuilder;
        this.fakeStoreApiClient = fakeStoreApiClient;
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
        FakeStoreProductDto fakeStoreProductDto = fakeStoreApiClient.getProduct(productId);
        Product product = getProductFromFakeProductDto(fakeStoreProductDto);
        return product;
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
