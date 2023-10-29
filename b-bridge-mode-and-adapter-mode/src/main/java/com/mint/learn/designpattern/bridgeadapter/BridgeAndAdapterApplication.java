package com.mint.learn.designpattern.bridgeadapter;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = {"com.mint.learn.designpattern.bridgeadapter.mapper"})
public class BridgeAndAdapterApplication {
    public static void main(String[] args) {
        SpringApplication.run(BridgeAndAdapterApplication.class, args);
    }
}
