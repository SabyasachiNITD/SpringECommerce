package com.myecommerrce.productcatalogserviceproxy.services;

import com.myecommerrce.productcatalogserviceproxy.dtos.ProductDto;

public interface IProductService {
    String getProducts();

    String getProduct(String productId);

    String createProduct(ProductDto productDto);

    String updateProduct(ProductDto productDto);
}
