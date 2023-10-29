package com.mint.learn.designpattern.bridgeadapter.controller;

import com.mint.learn.designpattern.bridgeadapter.adapter.Login3rdAdapter;
import com.mint.learn.designpattern.bridgeadapter.entity.po.UserInfoPO;
import com.mint.learn.designpattern.bridgeadapter.service.UserBridgeService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/v1/user/bridge")
public class UserBridgeController {
    @Resource
    private UserBridgeService userBridgeService;

    @PostMapping("/login")
    public String login(String account, String password) {
        return userBridgeService.login(account, password);
    }

    @PostMapping("/register")
    public String login(@RequestBody UserInfoPO userInfoPO) {
        return userBridgeService.register(userInfoPO);
    }

    @GetMapping("/gitee")
    public String gitee(HttpServletRequest request) {
        return userBridgeService.login3rd(request, "GITEE");
    }
}
