package com.akz.ky.service.impl;

import com.akz.ky.mapper.AlgorithmMapper;
import com.akz.ky.pojo.AlgorithmPojo;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class AlgorithmServiceImpl {
    public static List<AlgorithmPojo> list = new ArrayList<>();
    @Autowired
    AlgorithmMapper algorithmMapper;

    public void init(){
        list = algorithmMapper.list();
    }
    public AlgorithmPojo get(String type){
        AlgorithmPojo algorithmPojo = null;
        for (AlgorithmPojo a: list) {
            if (a.getType().equals(type)){
                    algorithmPojo = a;
            }
        }
        return algorithmPojo;
    }
}
