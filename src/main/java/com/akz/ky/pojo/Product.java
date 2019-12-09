package com.akz.ky.pojo;

import lombok.Data;

@Data
public class Product {
    private int id;
    private String name;
    private float price;
    public Product(int id,String name,float price){
        this.id = id;
        this.name = name;
        this.price = price;
    }
}
