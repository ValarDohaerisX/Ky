package com.akz.ky.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.DateFormat;
import java.util.Date;

@RestController
public class TestController {


    @RequestMapping("/hello")
    public String print(Model model){
//        model.addAttribute("Hello World!");
        System.out.println("Hello World!Hello World!");
        model.addAttribute("now", DateFormat.getDateInstance().format(new Date()));
        return "a";
    }
}
