package com.mint.learn.designpattern.bridgeadapter.bridge;

import com.mint.learn.designpattern.bridgeadapter.entity.po.UserInfoPO;

import javax.servlet.http.HttpServletRequest;

public interface RegisterLoginFuncInterface {
    String login(String account, String password);
    String register(UserInfoPO userInfoPO);
    boolean checkUserExists(String username);
    String login3rd(HttpServletRequest request);
}
