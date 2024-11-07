package com.mofan.mfbolg.utils;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 博客网站全局统一返回结果类
 */
@Data
@ApiModel(value = "博客网站全局统一返回结果")
public class BlogResult<T> {

    @ApiModelProperty(value = "返回码")
    private Integer code;

    @ApiModelProperty(value = "返回消息")
    private String message;

    @ApiModelProperty(value = "返回数据")
    private T data;

    // 默认构造函数
    public BlogResult() {}

    // 接受 code, message 参数的构造函数
    public BlogResult(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    // 接受 code, data 参数的构造函数
    public BlogResult(Integer code, T data) {
        this.code = code;
        this.data = data;
    }

    // 接受 code, data, message 参数的构造函数
    public BlogResult(Integer code, T data, String message) {
        this.code = code;
        this.data = data;
        this.message = message;
    }


    // 构建返回结果
    public static <T> BlogResult<T> build(T data, Integer code, String message) {
        BlogResult<T> result = new BlogResult<>();
        result.setData(data);
        result.setCode(code);
        result.setMessage(message);
        return result;
    }

    public static <T> BlogResult<T> ok(T data) {
        return build(data, BlogResultCodeEnum.SUCCESS.getCode(), BlogResultCodeEnum.SUCCESS.getMessage());
    }

    public static <T> BlogResult<T> fail(T data) {
        return build(data, BlogResultCodeEnum.FAIL.getCode(), BlogResultCodeEnum.FAIL.getMessage());
    }

    // 构建失败的结果，仅包含消息
    public static <T> BlogResult<T> fail(String message) {
        return new BlogResult<>(BlogResultCodeEnum.FAIL.getCode(), null, message);
    }

    public BlogResult<T> message(String msg) {
        this.setMessage(msg);
        return this;
    }

    public BlogResult<T> code(Integer code) {
        this.setCode(code);
        return this;
    }

    public boolean isOk() {
        return this.getCode().intValue() == BlogResultCodeEnum.SUCCESS.getCode().intValue();
    }
}