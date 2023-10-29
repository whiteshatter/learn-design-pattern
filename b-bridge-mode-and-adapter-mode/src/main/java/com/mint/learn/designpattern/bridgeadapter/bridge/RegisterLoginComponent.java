package com.mint.learn.designpattern.bridgeadapter.bridge;

import com.mint.learn.designpattern.bridgeadapter.entity.po.UserInfoPO;

import javax.servlet.http.HttpServletRequest;

public class RegisterLoginComponent extends AbstractRegisterLoginComponent {
    public RegisterLoginComponent(RegisterLoginFuncInterface funcInterface) {
        super(funcInterface);
    }

    @Override
    public String login(String username, String password) {
        return funcInterface.login(username, password);
    }

    @Override
    public String register(UserInfoPO userInfoPO) {
        return funcInterface.register(userInfoPO);
    }

    @Override
    public boolean checkUserExists(String username) {
        return funcInterface.checkUserExists(username);
    }

    @Override
    public String login3rd(HttpServletRequest request) {
        return funcInterface.login3rd(request);
    }
}
