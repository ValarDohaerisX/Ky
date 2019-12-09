package com.akz.ky.controller;

import com.akz.ky.pojo.Product;
import com.akz.ky.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class ProductController {

    @Autowired
    ProductService productService;

    @RequestMapping("/products")
    public String listProducts(Model model){
        List<Product> products = productService.listProducts();
        model.addAttribute("ps",products);
        return "products";
    }
}
