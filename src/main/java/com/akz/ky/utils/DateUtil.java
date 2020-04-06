package com.akz.ky.utils;

import cn.hutool.core.date.DateTime;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author lzx
 * @version 1.0
 * @date 2020/1/15 21:55
 * @Description 日期工具类
 */
public class DateUtil {
    public static final String formatPattern1 = "yyyy-MM-dd HH:ss:mm";
    public static final String formatPattern2 = "yyyy-MM-dd";
    public static final String formatPattern3 = "HH:ss:mm";
    public static  SimpleDateFormat sdf = null;

    public DateUtil(){

    }
    /**
     * 将字符串解析为日期格式
     * @param sdate
     * @return Date
     */
    public Date formatString(String sdate,String pattern) throws ParseException {
//        sdf = new SimpleDateFormat(pattern);
        sdf = new SimpleDateFormat();
        sdf.applyPattern(pattern);
        return sdf.parse(sdate);
    }

    /**
     * 将日期格式化为字符串
     * @param date
     * @return String
     */
    public String formatDate(Date date,String pattern){
//        sdf = new SimpleDateFormat(pattern);
        sdf = new SimpleDateFormat();
        sdf.applyPattern(pattern);
        return sdf.format(date);
    }

    //获取当前日期
    public String getCurrDate(){
//        sdf = new SimpleDateFormat(formatPattern1);
        sdf = new SimpleDateFormat();
        sdf.applyPattern(formatPattern2);
        return sdf.format(new Date());
    }

    //获取当前时间
    public String getCurrTime(){
//        sdf = new SimpleDateFormat(formatPattern1);
        sdf = new SimpleDateFormat();
        sdf.applyPattern(formatPattern3);
        return sdf.format(new Date());
    }
    //获取特定模式的日期
    public String getCurrDate(String pattern){
        sdf = new SimpleDateFormat();
        sdf = new SimpleDateFormat(pattern);
        return sdf.format(new Date());
    }

    public Timestamp getCurrTimestamp(){
        sdf = new SimpleDateFormat();
        sdf = new SimpleDateFormat(formatPattern1);
        sdf.setLenient(false);
        String s = sdf.format(new Date(System.currentTimeMillis()));
        System.out.println("s:"+s);
        Timestamp timestamp = null;
//        Date parse = null;
        try {
            timestamp = new Timestamp(sdf.parse(s).getTime());
//            parse = sdf.parse(s);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        new DateTime();
//        return new Timestamp(new Date().getTime());
        return timestamp;
    }

    public static void main(String[] args) {
//        Timestamp currTimestamp = new DateUtil().getCurrTimestamp();
//        System.out.println(currTimestamp);
        String currDate = new DateUtil().getCurrDate();
        System.out.println(currDate);
    }
}
