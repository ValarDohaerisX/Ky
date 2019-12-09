package com.akz.ky.message;

import java.io.Serializable;

/**
 * @author lzx
 * @version 1.0
 * @date 2019/9/27 10:36
 */
public class AbstractReturnCode implements Serializable{
    private String name;
    private final int code;
    private final String desc;

    public AbstractReturnCode(){
        this.code = -1;
        this.desc = null;
    }

    public AbstractReturnCode(String desc,int code){
        this.code = code;
        this.desc = desc;
    }

    public AbstractReturnCode(int code){
        this.desc = null;
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}
