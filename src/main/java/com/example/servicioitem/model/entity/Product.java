package com.example.servicioitem.model.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Product {

    private Integer id;
    private String productname;
    private Double unitprice;
    private Integer unitsinstock;
    private Integer unitsonorder;
    private Supplier supplier;
    private Category category;
    private String quantityperunit;
    private Integer reorderlevel;
    private Boolean discontinued;
    private int port;
}
