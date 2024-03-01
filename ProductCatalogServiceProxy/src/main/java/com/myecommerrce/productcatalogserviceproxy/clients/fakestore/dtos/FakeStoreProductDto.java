package com.myecommerrce.productcatalogserviceproxy.clients.fakestore.dtos;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class FakeStoreProductDto {
    private Long id;
    private String title;
    private String description;
    private String image;
    private Double price;
    private String category;
    private FakeStoreRatingDto ratingDto;
}
