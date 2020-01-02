package com.akz.ky.pojo;

import lombok.Data;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

/**
 * @author lzx
 * @version 1.0
 * @date 2019/12/9 19:36
 * @Description 公共代码表
 */

@Data
public class LdcodePojo {

    int codeNo;

    String codeType;

    String Code;

    String codeName;

//    public static void main(String[] args) {
//        Callable c =  new Callable<LdcodePojo>();
//        FutureTask futureTask = new FutureTask<new Callable<LdcodePojo>()>();
//
//    }

}
