package com.myecommerrce.productcatalogserviceproxy.dtos;

import com.myecommerrce.productcatalogserviceproxy.models.Category;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ProductDto {
    private String title;
    private String description;
    private String imageUrl;
    private Double price;
    private Category category;
}
