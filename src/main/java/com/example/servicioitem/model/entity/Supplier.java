package com.example.servicioitem.model.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Supplier {

    public Integer id;
    public String companyname;
    public String contactname;
    public String address;
    public String city;
    public Object region;
    public String postalcode;
    public String country;
    public String phone;
    public Object fax;
    public Object homepage;
}
