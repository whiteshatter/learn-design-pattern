package com.mint.learn.designpattern.bridgeadapter.service;

import com.mint.learn.designpattern.bridgeadapter.entity.po.UserInfoPO;

import javax.servlet.http.HttpServletRequest;

public interface UserBridgeService {
    String login(String account, String password);

    String register(UserInfoPO userInfoPO);

    String login3rd(HttpServletRequest request, String type);
}
