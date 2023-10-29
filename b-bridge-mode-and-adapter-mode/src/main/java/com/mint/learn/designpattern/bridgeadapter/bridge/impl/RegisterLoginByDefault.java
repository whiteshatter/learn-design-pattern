package com.mint.learn.designpattern.bridgeadapter.bridge.impl;

import com.mint.learn.designpattern.bridgeadapter.bridge.AbstractRegisterLoginFunc;
import com.mint.learn.designpattern.bridgeadapter.bridge.RegisterLoginComponentFactory;
import com.mint.learn.designpattern.bridgeadapter.bridge.RegisterLoginFuncInterface;
import com.mint.learn.designpattern.bridgeadapter.dao.UserInfoDao;
import com.mint.learn.designpattern.bridgeadapter.entity.po.UserInfoPO;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.time.LocalDateTime;

@Component
public class RegisterLoginByDefault extends AbstractRegisterLoginFunc implements RegisterLoginFuncInterface {
    @Resource
    private UserInfoDao userInfoDao;

    @PostConstruct
    private void initFuncMap() {
        RegisterLoginComponentFactory.funcMap.put("Default", this);
    }

    @Override
    public String login(String account, String password) {
        return super.commonLogin(account, password, userInfoDao);
    }

    @Override
    public String register(UserInfoPO userInfoPO) {
        return super.commonRegister(userInfoPO, userInfoDao);
    }

    public boolean checkUserExists(String username) {
        return super.checkUserExists(username);
    }
}
