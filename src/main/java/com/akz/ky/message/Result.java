package com.akz.ky.message;

import java.io.Serializable;

/**
 * @author lzx
 * @version 1.0
 * @date 2019/9/27 10:28
 */
public class Result<T> implements Serializable {

    private AbstractReturnCode returnCode;

    private T body;


    private Result(){}

    public static <T> Result<T> success(T body){
        Result<T> result = new Result<>();
        result.setBody(body);
        result.setReturnCode(ApiReturnCode.SUCCESS);

        return  result;
    }

    public static <T> Result<T> failure(AbstractReturnCode abstractReturnCode){
        Result<T> result = new Result<>();
        result.setReturnCode(abstractReturnCode);
        return result;
    }

    public static <T> Result<T> failure(AbstractReturnCode abstractReturnCode, String message){
        Result<T> result = new Result<>();
        result.setReturnCode(new AbstractReturnCode(message,abstractReturnCode.getCode()));
        return result;
    }
    public AbstractReturnCode getReturnCode() {
        return this.returnCode;
    }

//    public int getCode(){ return returnCode.getCode();}

    public void setReturnCode(AbstractReturnCode returnCode) {
        this.returnCode = returnCode;
    }

//    public String getMessage(){ return returnCode.getDesc();}

    public T getBody() {
        return body;
    }

    public void setBody(T body) {
        this.body = body;
    }

}
