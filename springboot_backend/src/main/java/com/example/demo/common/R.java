package com.example.demo.common;

import lombok.Data;

import java.util.Map;

@Data
public class R<T> {
    private Integer code;
    private Boolean success;
    private String message;
    private Map<String, Object> data;
    private T result ;

    public R() {
    }

    public static R ok(String msg) {
        R r = new R();
        r.setCode(ResultCode.SUCCESS);
        r.setSuccess(true);
        r.setMessage(msg);
        return r;
    }

    public static R error(String msg) {
        R r = new R();
        r.setCode(ResultCode.ERROR);
        r.setSuccess(false);
        r.setMessage(msg);
        return r;
    }

    public R code(Integer code) {
        this.setCode(code);
        return this;
    }

    public R success(Boolean success) {
        this.setSuccess(success);
        return this;
    }
    public R message(String message) {
        this.setMessage(message);
        return this;
    }


    public R data(String key,String value) {
        this.data.put(key,value);
        return this;
    }

    public R data(Map<String, Object> map) {
        this.setData(map);
        return this;
    }
}



