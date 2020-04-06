package com.akz.ky.utils;

import com.alibaba.fastjson.JSON;

import java.util.Map;

/**
 * @author lzx
 * @version 1.0
 * @date 2020/3/30 20:57
 * @Description 将接收参数定义为Map<String, Object>，然后使用map转object工具，转换成需要的对象。
 */
public class JsonXMLUtils {
    public static String obj2json(Object obj) throws Exception {
        return JSON.toJSONString(obj);
    }

    public static <T> T json2obj(String jsonStr, Class<T> clazz) throws Exception {
        return JSON.parseObject(jsonStr, clazz);
    }

    public static <T> Map<String, Object> json2map(String jsonStr)     throws Exception {
        return JSON.parseObject(jsonStr, Map.class);
    }

    public static <T> T map2obj(Map<?, ?> map, Class<T> clazz) throws Exception {
        return JSON.parseObject(JSON.toJSONString(map), clazz);
    }
}
