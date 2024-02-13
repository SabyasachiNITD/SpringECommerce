package com.myecommerrce.productcatalogserviceproxy.models;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public abstract class BaseModel {
    private long id;
    private Date createdDate;
    private Date lastModifiedDate;
    private Status status;
}
