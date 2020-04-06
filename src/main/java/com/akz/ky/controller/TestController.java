package com.akz.ky.controller;

import com.akz.ky.pojo.UserPojo;
import com.akz.ky.utils.JsonXMLUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.DateFormat;
import java.util.Date;
import java.util.Map;

@RestController
public class TestController {

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @Autowired
    RedisTemplate redisTemplate;
    @GetMapping(value = "/redis/user")
    public String user() throws Exception {
        UserPojo userPojo = new UserPojo();
        userPojo.setUserName("abc");
        userPojo.setUserNo(1);
        redisTemplate.opsForValue().set("1",userPojo);
//        stringRedisTemplate.opsForValue().set("1","abc");

//        return JsonXMLUtils.json2obj(redisTemplate.opsForValue().get("1").toString(),UserPojo.class);
        return "success";
    }
//    @RequestMapping("/redis/user")
//    public String print(Model model){
////        model.addAttribute("Hello World!");
//        System.out.println("Hello World!Hello World!");
//        model.addAttribute("now", DateFormat.getDateInstance().format(new Date()));
//        return "a";
//    }
}
