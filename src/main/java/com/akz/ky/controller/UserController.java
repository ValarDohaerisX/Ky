package com.akz.ky.controller;

import com.akz.ky.pojo.ActivityPojo;
import com.akz.ky.pojo.UserPojo;
import com.akz.ky.service.ActivityService;
import com.akz.ky.service.UserService;
import com.akz.ky.service.impl.ActivityServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;


@RestController
public class UserController {

    @Autowired
    UserService userService;

    @Autowired(required = false)
    ActivityService activityService;

    @PostMapping("login")
    public String login(@RequestBody UserPojo userPojo, HttpServletRequest request){
//        UserPojo user = userService.get(userPojo.getName());
//        if (!user.getPassword().equals("")&&user.getPassword().equals(userPojo.getPassword())){
//            HttpSession session = request.getSession();
////            user.setActivity();
//            List<ActivityPojo> list = ActivityServiceImpl.list;
//            for (ActivityPojo ap:list) {
//                if (user.getActivity()<ap.getLevel()){
//                    user.setBadge(ap.getName());
//                    break;
//                }
//                }
//            user.setRegTime(new Timestamp(System.currentTimeMillis()));
//            user.setLastLoginTime(new Timestamp(System.currentTimeMillis()));
//            session.setAttribute("user",user);
//            return "login";
//        }
        return "";
    }
    @PostMapping("register")
    public String register(@RequestBody UserPojo userPojo){
//        if (userPojo == null)
//            return "输入错误，请重新输入";
//        UserPojo user = userService.get(userPojo.getName());
//        if(user == null){
//            userPojo.setUserType("1");
//            userPojo.setUserPemission(1);
//            userPojo.setActivity(0);
//            List<ActivityPojo> list = ActivityServiceImpl.list;
//            for (ActivityPojo ap:list) {
//                if (userPojo.getActivity()<ap.getLevel()){
//                    userPojo.setBadge(ap.getName());
//                    break;
//                }
//            }
//            userPojo.setRegTime(new Timestamp(System.currentTimeMillis()));
//            userService.add(userPojo);
//
//        }else {
//            return "用户已存在";
//        }

        return "";
    }

    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<>();
        String s = "?*5+?*3+?*1-?*3";
        char[] cs = s.toCharArray();
        int a = 0;
        for (int i = 0; i < cs.length; i++) {
            if (i=='?'){
//                cs[i] = list.get(i).;
            }
        }
        System.out.println("a->"+a);
        for (int i = 0; i < a-1; i++) {
//            if(cs[i]=='?')
        }
        ScriptEngine se = new ScriptEngineManager().getEngineByName("JavaScript");
        try {
            String str = "2*3-45/5+9+9%5";
            int eval = (int) se.eval(str);
            System.out.println(eval);
        }catch (Exception e){
            e.printStackTrace();
        }

//        String[] split = s.split("\\d");
//        for (String ss :
//                split) {
//
//        }
    }
}
