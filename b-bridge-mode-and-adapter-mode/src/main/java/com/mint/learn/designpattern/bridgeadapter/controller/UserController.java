package com.mint.learn.designpattern.bridgeadapter.controller;

import com.mint.learn.designpattern.bridgeadapter.adapter.Login3rdAdapter;
import com.mint.learn.designpattern.bridgeadapter.entity.po.UserInfoPO;
import com.mint.learn.designpattern.bridgeadapter.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/v1/user")
public class UserController {
    @Resource
    private Login3rdAdapter login3rdAdapter;


    @PostMapping("/login")
    public String login(String account, String password) {
        return login3rdAdapter.login(account, password);
    }

    @PostMapping("/register")
    public String login(@RequestBody UserInfoPO userInfoPO) {
        return login3rdAdapter.register(userInfoPO);
    }

    @GetMapping("/gitee")
    public String gitee(String code, String state) {
        return login3rdAdapter.loginByGitee(code, state);
    }

}
