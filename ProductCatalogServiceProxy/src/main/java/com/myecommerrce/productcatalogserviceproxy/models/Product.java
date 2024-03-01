package com.myecommerrce.productcatalogserviceproxy.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Product extends BaseModel{
    private String title;
    private String description;
    private String imageUrl;
    private Double price;
    //private Category category;
}
