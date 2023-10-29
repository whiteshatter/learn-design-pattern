package com.mint.learn.designpattern.bridgeadapter.service.impl;

import com.mint.learn.designpattern.bridgeadapter.dao.UserInfoDao;
import com.mint.learn.designpattern.bridgeadapter.entity.po.UserInfoPO;
import com.mint.learn.designpattern.bridgeadapter.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;

@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserInfoDao userInfoDao;

    @Override
    public String login(String account, String password) {
        UserInfoPO userInfoPO = userInfoDao.selectByUsernameAndPassword(account, password);
        if (userInfoPO == null) {
            return "account/password error";
        }
        return "login success";
    }

    @Override
    public String register(UserInfoPO userInfoPO) {
        if (checkUserExists(userInfoPO.getUsername())) {
            throw new RuntimeException("user already registered");
        }
        userInfoPO.setCreateTime(LocalDateTime.now());
        userInfoDao.insert(userInfoPO);
        return "register success";
    }

    public boolean checkUserExists(String username) {
        UserInfoPO userInfoPO = userInfoDao.selectByUsername(username);
        if (userInfoPO == null) {
            return false;
        }
        return true;
    }
}
