package com.mint.learn.designpattern.bridgeadapter.bridge;

import com.mint.learn.designpattern.bridgeadapter.dao.UserInfoDao;
import com.mint.learn.designpattern.bridgeadapter.entity.po.UserInfoPO;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

public abstract class AbstractRegisterLoginFunc implements RegisterLoginFuncInterface {

    protected String commonLogin(String account, String password, UserInfoDao userInfoDao) {
        UserInfoPO userInfoPO = userInfoDao.selectByUsernameAndPassword(account, password);
        if (userInfoPO == null) {
            return "account/password error";
        }
        return "login success";
    }

    protected String commonRegister(UserInfoPO userInfoPO, UserInfoDao userInfoDao) {
        if (checkUserExists(userInfoPO.getUsername())) {
            throw new RuntimeException("user already registered");
        }
        userInfoPO.setCreateTime(LocalDateTime.now());
        userInfoDao.insert(userInfoPO);
        return "register success";
    }

    protected boolean commonCheckUserExists(String username, UserInfoDao userInfoDao) {
        UserInfoPO userInfoPO = userInfoDao.selectByUsername(username);
        if (userInfoPO == null) {
            return false;
        }
        return true;
    }

    @Override
    public String login(String account, String password) {
        throw new UnsupportedOperationException();
    }

    @Override
    public String register(UserInfoPO userInfoPO) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean checkUserExists(String username) {
        throw new UnsupportedOperationException();
    }

    @Override
    public String login3rd(HttpServletRequest request) {
        throw new UnsupportedOperationException();
    }
}
