package com.myecommerrce.productcatalogserviceproxy.clients.fakestore.dtos;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class FakeStoreRatingDto {
    private Double rate;
    private Long count;
}
