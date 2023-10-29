package com.mint.learn.designpattern.bridgeadapter.adapter;

public interface Login3rdTarget {
    String loginByGitee(String code, String state);

    String loginByWechat(String... args);

    String loginByQQ(String... args);
}
