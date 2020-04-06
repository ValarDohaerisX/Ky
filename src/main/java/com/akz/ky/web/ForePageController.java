package com.akz.ky.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author lzx
 * @version 1.0
 * @date 2020/3/6 15:08
 * @Description 前端页面跳转控制器
 */
@Controller
public class ForePageController {
    @GetMapping(value = "/")
    public String index(){
        return "redirect:index";
    }

    @GetMapping(value = "/index")
    public String returnIndex(){
        return "fore/index";
    }

    @GetMapping(value = "/schoolIndex")
    public String schoolIndex(){
        return "fore/schoolIndex";
    }

    @GetMapping(value = "/majorIndex")
    public String majorIndex(){
        return "fore/majorIndex";
    }

    @GetMapping(value = "/schoolDetail")
    public String schoolDetail(){
        return "fore/schoolDetail";
    }

    @GetMapping(value = "/schoolDetailDesc")
    public String schoolDetailDesc(){
        return "fore/schoolDetaill/schoolDetailDesc";
    }
//    院校公告
    @GetMapping(value = "/schoolDetailTitle")
    public String schoolDetailTitle(){
        return "fore/schoolDetaill/schoolDetailTitle";
    }
//    院校公告详情内容
    @GetMapping(value = "/schoolDetailInfoContent1")
    public String schoolDetailInfoContent1(){
        return "fore/schoolDetaill/schoolDetailInfoContent1";
    }

    @GetMapping(value = "/schoolDetailDepartment")
    public String schoolDetailDepartment(){
        return "fore/schoolDetaill/schoolDetailDepartment";
    }

    @GetMapping(value = "/schoolDetailGetStu")
    public String schoolDetailGetStu(){
        return "fore/schoolDetaill/schoolDetailGetStu";
    }

    @GetMapping(value = "/schoolDetailDispensing")
    public String schoolDetailDispensing(){
        return "fore/schoolDetaill/schoolDetailDispensing";
    }

    @GetMapping(value = "/schoolDetailMajor")
    public String schoolDetailMajor(){
        return "fore/schoolDetaill/schoolDetailMajor";
    }
    @GetMapping(value = "/foresearch")
    public String foreSearch(){
        return "fore/schoolDetail";
    }

    @GetMapping(value = "/initSchoolIndex")
    public String initSchoolIndex(){
        return "fore/schoolIndex";
    }

    @GetMapping(value = "/tieba")
    public String tieba(){
        return "fore/tieba";
    }

    @GetMapping(value = "/video")
    public String video(){
        return "fore/video";
    }
    public static void main(String[] args) {
        String loanEndDate = "2020-03-12";
        String pattern = "yyyy-MM-dd";
        SimpleDateFormat df = new SimpleDateFormat(pattern);
        Date today = new Date();
        String tString = df.format(today);
        int i = loanEndDate.compareTo(tString);
        System.out.println(tString);
        System.out.println(i);
        String str ="123456";
        String s = str.substring(str.length() - 3, str.length());
        System.out.println(s);
    }
}
