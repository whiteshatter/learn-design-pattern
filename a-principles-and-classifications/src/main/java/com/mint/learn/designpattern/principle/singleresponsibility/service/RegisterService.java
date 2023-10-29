package com.mint.learn.designpattern.principle.singleresponsibility.service;

/**
 * 注册及注册所需短信和邮箱处理
 */
public interface RegisterService {
    /**
     * 注册
     * @param args
     * @return
     */
    Object register(String... args);

    /**
     * 短信验证
     * @param phoneNum
     * @return
     */
    Object phoneCodeSend(String phoneNum);

    /**
     * 邮箱验证
     * @param emailAddress
     * @return
     */
    Object mailCodeSend(String emailAddress);
}
