package com.myecommerrce.productcatalogserviceproxy.dtos;

import com.myecommerrce.productcatalogserviceproxy.models.Category;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ProductDto {
    private String id;
    private String title;
    private String description;
    private String image;
    private Double price;
    private String category;
    private RatingDto ratingDto;
}
