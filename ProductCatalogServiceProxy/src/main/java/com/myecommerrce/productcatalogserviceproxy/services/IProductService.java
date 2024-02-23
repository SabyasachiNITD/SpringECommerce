package com.myecommerrce.productcatalogserviceproxy.services;

import com.myecommerrce.productcatalogserviceproxy.dtos.ProductDto;
import com.myecommerrce.productcatalogserviceproxy.models.Product;

import java.util.List;

public interface IProductService {
    List<Product> getProducts();

    Product getProduct(Long productId);

    String createProduct(ProductDto productDto);

    String updateProduct(ProductDto productDto);
}
