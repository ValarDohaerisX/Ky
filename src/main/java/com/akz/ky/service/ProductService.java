package com.akz.ky.service;

import com.akz.ky.pojo.Product;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {
    public List<Product> listProducts(){
        ArrayList<Product> list = new ArrayList<>();
        list.add(new Product(1,"product a", 50));
        list.add(new Product(2,"product b", 100));
        list.add(new Product(3,"product c", 150));
        return list;
    }
}
