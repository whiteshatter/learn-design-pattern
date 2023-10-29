package com.mint.learn.designpattern.bridgeadapter.entity.po;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserInfoPO {
    private Long id;

    private String username;

    private String password;

    private LocalDateTime createTime;

    private String email;
}
