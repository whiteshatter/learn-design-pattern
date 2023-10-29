package com.mint.learn.designpattern.bridgeadapter.bridge;

import com.mint.learn.designpattern.bridgeadapter.entity.po.UserInfoPO;

import javax.servlet.http.HttpServletRequest;

public abstract class AbstractRegisterLoginComponent {
    protected RegisterLoginFuncInterface funcInterface;
    public AbstractRegisterLoginComponent(RegisterLoginFuncInterface funcInterface) {
        validate(funcInterface);
        this.funcInterface = funcInterface;
    }

    private void validate(RegisterLoginFuncInterface funcInterface) {
        if (funcInterface == null) {
            throw new UnsupportedOperationException("register/login function is null");
        }
    }

    public abstract String login(String username, String password);
    public abstract String register(UserInfoPO userInfoPO);
    public abstract boolean checkUserExists(String username);
    public abstract String login3rd(HttpServletRequest request);
}
