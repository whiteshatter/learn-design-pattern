package com.mint.learn.designpattern.bridgeadapter.adapter;

import com.alibaba.fastjson.JSONObject;
import com.mint.learn.designpattern.bridgeadapter.entity.po.UserInfoPO;
import com.mint.learn.designpattern.bridgeadapter.service.impl.UserServiceImpl;
import com.mint.learn.designpattern.bridgeadapter.utils.HttpClientUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class Login3rdAdapter extends UserServiceImpl implements Login3rdTarget {
    @Value("${gitee.state}")
    private String giteeState;
    @Value("${gitee.token.url}")
    private String giteeTokenUrl;
    @Value("${gitee.user.url}")
    private String giteeUserUrl;
    @Value("${gitee.user.prefix}")
    private String giteeUserPrefix;
    @Override
    public String loginByGitee(String code, String state) {
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
        if (super.checkUserExists(userName)) {
            return super.login(userName, password);
        }
        UserInfoPO userInfoPO = new UserInfoPO();
        userInfoPO.setUsername(userName);
        userInfoPO.setPassword(password);
        userInfoPO.setCreateTime(LocalDateTime.now());
        super.register(userInfoPO);
        return super.login(userName, password);
    }

    @Override
    public String loginByWechat(String... args) {
        return null;
    }

    @Override
    public String loginByQQ(String... args) {
        return null;
    }
}
