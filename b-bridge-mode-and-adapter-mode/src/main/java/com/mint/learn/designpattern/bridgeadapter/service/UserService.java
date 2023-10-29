package com.mint.learn.designpattern.bridgeadapter.service;

import com.mint.learn.designpattern.bridgeadapter.entity.po.UserInfoPO;

public interface UserService {
    String login(String account, String password);

    String register(UserInfoPO userInfoPO);
}
