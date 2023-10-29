package com.mint.learn.designpattern.bridgeadapter.bridge.impl;

import com.alibaba.fastjson.JSONObject;
import com.mint.learn.designpattern.bridgeadapter.bridge.AbstractRegisterLoginFunc;
import com.mint.learn.designpattern.bridgeadapter.bridge.RegisterLoginComponentFactory;
import com.mint.learn.designpattern.bridgeadapter.bridge.RegisterLoginFuncInterface;
import com.mint.learn.designpattern.bridgeadapter.dao.UserInfoDao;
import com.mint.learn.designpattern.bridgeadapter.entity.po.UserInfoPO;
import com.mint.learn.designpattern.bridgeadapter.utils.HttpClientUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

@Component
public class RegisterLoginByGitee extends AbstractRegisterLoginFunc implements RegisterLoginFuncInterface {
    @Value("${gitee.state}")
    private String giteeState;
    @Value("${gitee.token.url}")
    private String giteeTokenUrl;
    @Value("${gitee.user.url}")
    private String giteeUserUrl;
    @Value("${gitee.user.prefix}")
    private String giteeUserPrefix;

    @Resource
    private UserInfoDao userInfoDao;

    @PostConstruct
    private void initFuncMap() {
        RegisterLoginComponentFactory.funcMap.put("GITEE", this);
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

    @Override
    public String login3rd(HttpServletRequest request) {
        String state = request.getParameter("state");
        String code = request.getParameter("code");
        if (!giteeState.equals(state)) {
            throw new UnsupportedOperationException("Invalid state");
        }
        String tokenUrl = giteeTokenUrl.concat(code);
        JSONObject tokenResponse = HttpClientUtil.execute(tokenUrl, HttpMethod.POST);
        String token = String.valueOf(tokenResponse.get("access_token"));
        String userUtl = giteeUserUrl.concat(token);
        JSONObject userResponse = HttpClientUtil.execute(userUtl, HttpMethod.GET);
        String userName = giteeUserPrefix.concat(String.valueOf(userResponse.get("name")));
        return autoRegister3rdAndLogin(userName, userName);
    }

    private String autoRegister3rdAndLogin(String userName, String password) {
        if (checkUserExists(userName)) {
            return login(userName, password);
        }
        UserInfoPO userInfoPO = new UserInfoPO();
        userInfoPO.setUsername(userName);
        userInfoPO.setPassword(password);
        userInfoPO.setCreateTime(LocalDateTime.now());
        register(userInfoPO);
        return login(userName, password);
    }
}
