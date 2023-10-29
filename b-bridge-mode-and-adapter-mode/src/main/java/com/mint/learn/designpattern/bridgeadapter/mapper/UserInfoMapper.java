package com.mint.learn.designpattern.bridgeadapter.mapper;

import com.mint.learn.designpattern.bridgeadapter.entity.po.UserInfoPO;
import org.apache.ibatis.annotations.Param;

public interface UserInfoMapper {
    int insert(@Param("userInfoPO") UserInfoPO userInfoPO);

    UserInfoPO selectByUsernameAndPassword(@Param("username") String username, @Param("password") String password);

    UserInfoPO selectByUsername(@Param("username") String username);
}