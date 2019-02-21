package com.lusiwei.common;

import lombok.Getter;

public class ResponseResult<T> {
    private int status;
    private String msg;
    private T data;

    ResponseResult() {

    }
    @Getter
    public enum ResponseCode{
        SUCCESS(100,"成功"),
        ERROR(000, "失败");
        private Integer code;
        private String message;

        ResponseCode(Integer code, String message) {
            this.code = code;
            this.message = message;
        }
    }

    private ResponseResult(int status) {
        this.status = status;
    }

    private ResponseResult(int status, String msg) {
        this.status = status;
        this.msg = msg;
    }

    private ResponseResult(int status, String msg, T data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }

    private ResponseResult(int status, T data) {
        this.status = status;
        this.data = data;
    }

    public int getStatus() {
        return status;
    }

    public String getMsg() {
        return msg;
    }

    public T getData() {
        return data;
    }
    public boolean isSuccess() {
        return this.status == ResponseCode.SUCCESS.getCode();
    }

    public static <T> ResponseResult<T> success() {
        return new ResponseResult<>(ResponseCode.SUCCESS.getCode());
    }

    public static <T> ResponseResult<T> success(String msg) {
        return new ResponseResult<>(ResponseCode.SUCCESS.getCode(), msg);
    }

    public static <T> ResponseResult<T> success(T data) {
        return new ResponseResult<>(ResponseCode.SUCCESS.getCode(), data);
    }

    public static <T> ResponseResult<T> success(String msg, T data) {
        return new ResponseResult<>(ResponseCode.SUCCESS.getCode(), msg, data);
    }

    public static <T> ResponseResult<T> fail() {
        return new ResponseResult<>(ResponseCode.ERROR.getCode(), ResponseCode.ERROR.getMessage());
    }

    public static <T> ResponseResult<T> fail(String errorMessage) {
        return new ResponseResult<>(ResponseCode.ERROR.getCode(), errorMessage);
    }

    public static <T> ResponseResult<T> fail(int errorCode, String errorMessage) {
        return new ResponseResult<>(errorCode, errorMessage);
    }


}