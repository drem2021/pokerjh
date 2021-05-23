package com.github.drem2021.pokerjh.opt;

public class R<T> {
    private int code;
    private boolean success;
    private String msg;
    private T data;
    private int count;

    public R(){}

    public R(int code, boolean success, String msg, T data, int count) {
        this.code = code;
        this.success = success;
        this.msg = msg;
        this.data = data;
        this.count = count;
    }
    public R(int code, String msg) {
        this.code = code;
        this.success = true;
        this.msg = msg;
    }
    public R(String msg,T data) {
        this.code = 200;
        this.success = true;
        this.msg = msg;
        this.data = data;
    }
    public R ok(int code) {
        this.setCode(code);
        this.setSuccess(true);
        return this;
    }
    public R ok(int code, String msg) {
        this.setCode(code);
        this.setMsg(msg);
        this.setSuccess(true);
        return this;
    }
    public R ok(int code, String msg, T data) {
        this.setCode(code);
        this.setMsg(msg);
        this.setSuccess(true);
        this.setData(data);
        return this;
    }
    /**
     *
     * @param code
     * @param count
     * @param msg
     * @param data
     * @return
     */
    public R ok(int code,int count, String msg, T data) {
        this.setCode(code);
        this.setCount(count);
        this.setMsg(msg);
        this.setSuccess(true);
        this.setData(data);
        return this;
    }
    public R ok(String msg) {
        this.setCode(200);
        this.setMsg(msg);
        this.setSuccess(true);
        return this;
    }

    public R ok(String msg,boolean success) {
        this.setCode(200);
        this.setMsg(msg);
        this.setSuccess(success);
        return this;
    }
    public R ok(String msg, T data) {
        this.setCode(200);
        this.setMsg(msg);
        this.setData(data);
        this.setSuccess(true);
        return this;
    }
    public R error(int code) {
        this.setCode(code);
        this.setSuccess(false);
        return this;
    }
    public R error(int code, String msg) {
        this.setCode(code);
        this.setMsg(msg);
        this.setSuccess(false);
        return this;
    }
    public R error(int code, String msg, T data) {
        this.setCode(code);
        this.setMsg(msg);
        this.setData(data);
        this.setSuccess(false);
        return this;
    }
    public R error(String msg) {
        this.setCode(500);
        this.setMsg(msg);
        this.setSuccess(false);
        return this;
    }
    public R error(String msg, T data) {
        this.setCode(500);
        this.setMsg(msg);
        this.setData(data);
        this.setSuccess(false);
        return this;
    }

    public R code(int code) {
        this.setCode(code);
        return this;
    }
    public R msg(String msg) {
        this.setMsg(msg);
        return this;
    }
    public R count(int count) {
        this.setCount(count);
        return this;
    }
    public R success(boolean success) {
        this.setSuccess(success);
        return this;
    }
    public R data(T data) {
        this.setData(data);
        return this;
    }


    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
