package com.mint.learn.designpattern.principle.singleresponsibility.service;

/**
 * 登录服务
 */
public interface LoginService {
    /**
     * 默认登录方式
     * @param args
     * @return
     */
    Object doDefaultLogin(String... args);

    /**
     * 第三方登录
     * @param args
     * @return
     */
    Object do3rdPartLogin(String... args);

    /**
     * 第三方账号授权验证
     */
    Object valid3rdAccount(String... args);
}
