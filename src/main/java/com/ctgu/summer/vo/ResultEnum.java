package com.ctgu.summer.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @ClassName ResultEnum
 * @Description TODO
 * @Author wby
 * @Data 2021/7/15 16:59
 * @Version 1.0
 */
@Getter
@AllArgsConstructor
public enum ResultEnum {
    SUCCESS("请求成功",200),
    FAIL("操作失败",400),
    UNAUTHORIZED("尚未登录,请登录!",401),
    NOT_FOUND("页面不存在",404),
    INTERNAL_SERVER_ERROR("服务器繁忙",500),
    PARAMETER_ERROR("参数错误",-1),
    LOGIN_FAILED("用户名或密码错误",1000),
    PERMISSION_DENIED("权限不足",403);

    public String msg;
    public int code;
}
