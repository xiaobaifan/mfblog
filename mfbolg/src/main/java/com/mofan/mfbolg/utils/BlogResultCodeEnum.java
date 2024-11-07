package com.mofan.mfbolg.utils;

import lombok.Getter;

/**
 * @program: SmartCampus
 * @description: 统一返回结果状态信息类
 * @author: mofan
 * @create: 2023-04-28 18:53
 **/
/**
 * 博客网站统一返回结果状态信息类
 */
@Getter
public enum BlogResultCodeEnum {

    SUCCESS(200,"操作成功"),
    FAIL(201, "操作失败"),
    POST_NOT_FOUND(202, "文章未找到"),
    CATEGORY_NOT_FOUND(203, "分类未找到"),
    USER_NOT_FOUND(204, "用户未找到"),
    COMMENT_NOT_FOUND(205, "评论未找到"),
    LOGIN_ERROR(206, "登录错误"),
    REGISTRATION_ERROR(207, "注册错误"),
    PERMISSION_DENIED(208, "权限不足"),
    ;

    private Integer code;

    private String message;

    private BlogResultCodeEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}