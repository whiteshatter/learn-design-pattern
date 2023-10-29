package com.mint.learn.designpattern.bridgeadapter.service.impl;

import com.mint.learn.designpattern.bridgeadapter.bridge.AbstractRegisterLoginComponent;
import com.mint.learn.designpattern.bridgeadapter.bridge.RegisterLoginComponentFactory;
import com.mint.learn.designpattern.bridgeadapter.entity.po.UserInfoPO;
import com.mint.learn.designpattern.bridgeadapter.service.UserBridgeService;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
public class UserBridgeServiceImpl implements UserBridgeService {
    @Override
    public String login(String account, String password) {
        AbstractRegisterLoginComponent component = RegisterLoginComponentFactory.getComponent("Default");
        return component.login(account, password);
    }

    @Override
    public String register(UserInfoPO userInfoPO) {
        AbstractRegisterLoginComponent component = RegisterLoginComponentFactory.getComponent("Default");
        return component.register(userInfoPO);
    }

    @Override
    public String login3rd(HttpServletRequest request, String type) {
        AbstractRegisterLoginComponent component = RegisterLoginComponentFactory.getComponent(type);
        return component.login3rd(request);
    }
}
