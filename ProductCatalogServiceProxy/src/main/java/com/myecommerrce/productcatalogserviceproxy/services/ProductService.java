package com.myecommerrce.productcatalogserviceproxy.services;

import com.myecommerrce.productcatalogserviceproxy.dtos.ProductDto;
import com.myecommerrce.productcatalogserviceproxy.models.Category;
import com.myecommerrce.productcatalogserviceproxy.models.Product;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService implements IProductService {
    private RestTemplateBuilder restTemplateBuilder;

    public ProductService(RestTemplateBuilder restTemplateBuilder){
        this.restTemplateBuilder = restTemplateBuilder;
    }

    @Override
    public List<Product> getProducts(){
        RestTemplate restTemplate = restTemplateBuilder.build();
        ProductDto[] productDtos = restTemplate.getForEntity("https://fakestoreapi.com/products", ProductDto[].class).getBody();
        List<Product> products = new ArrayList<>();
        for(ProductDto productDto : productDtos){
            Product product = getProductFromProductDto(productDto);
            products.add(product);
        }
        return products;
    }
    @Override
    public Product getProduct(Long productId){

        RestTemplate restTemplate = restTemplateBuilder.build();
        // getForEntity also provides a ResposeEntity ---- we just get the body of the Entity
        ProductDto productDto = restTemplate.getForEntity("https://fakestoreapi.com/products/{id}", ProductDto.class, productId).getBody();
        return getProductFromProductDto(productDto);
    }
    @Override
    public Product createProduct(ProductDto productDto){
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<ProductDto> responseEntity = restTemplate.postForEntity("http://fakestoreapi.com/products", productDto,ProductDto.class);
        return getProductFromProductDto(responseEntity.getBody());
    }
    @Override
    public String updateProduct(ProductDto productDto){
        return null;
    }

    private Product getProductFromProductDto(ProductDto productDto){
        Product product = new Product();
        product.setTitle(productDto.getTitle());
        product.setDescription(productDto.getDescription());
        product.setPrice(productDto.getPrice());
        product.setImageUrl(productDto.getImage());
        Category category = new Category();
        category.setName(productDto.getCategory());
        product.setCategory(category);
        product.setId(productDto.getId());
        return product;
    }
}
