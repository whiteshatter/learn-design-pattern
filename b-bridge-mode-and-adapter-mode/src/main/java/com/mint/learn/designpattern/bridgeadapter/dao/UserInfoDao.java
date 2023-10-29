package com.mint.learn.designpattern.bridgeadapter.dao;

import com.mint.learn.designpattern.bridgeadapter.entity.po.UserInfoPO;

public interface UserInfoDao {

    int insert(UserInfoPO userInfoPO);

    UserInfoPO selectByUsernameAndPassword(String username, String password);

    UserInfoPO selectByUsername(String username);
}
