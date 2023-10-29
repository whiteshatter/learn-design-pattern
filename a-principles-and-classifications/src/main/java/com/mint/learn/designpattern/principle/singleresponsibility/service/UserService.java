package com.mint.learn.designpattern.principle.singleresponsibility.service;

/**
 * 用户服务
 */
public interface UserService {
    /**
     * 注册
     * @param args
     * @return
     */
    Object register(String... args);

    /**
     * 登录
     */
    Object login(String... args);
}
