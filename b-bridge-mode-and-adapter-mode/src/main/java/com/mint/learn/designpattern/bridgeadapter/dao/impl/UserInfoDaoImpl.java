package com.mint.learn.designpattern.bridgeadapter.dao.impl;

import com.mint.learn.designpattern.bridgeadapter.dao.UserInfoDao;
import com.mint.learn.designpattern.bridgeadapter.entity.po.UserInfoPO;
import com.mint.learn.designpattern.bridgeadapter.mapper.UserInfoMapper;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository
public class UserInfoDaoImpl implements UserInfoDao {
    @Resource
    private UserInfoMapper userInfoMapper;

    @Override
    public int insert(UserInfoPO userInfoPO) {
        return userInfoMapper.insert(userInfoPO);
    }

    @Override
    public UserInfoPO selectByUsernameAndPassword(String username, String password) {
        return userInfoMapper.selectByUsernameAndPassword(username, password);
    }

    @Override
    public UserInfoPO selectByUsername(String username) {
        return userInfoMapper.selectByUsername(username);
    }
}
