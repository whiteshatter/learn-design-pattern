package com.mint.learn.designpattern.bridgeadapter.bridge;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class RegisterLoginComponentFactory {
    public static final Map<String, AbstractRegisterLoginComponent> componentMap = new ConcurrentHashMap<>();

    public static Map<String, RegisterLoginFuncInterface> funcMap = new ConcurrentHashMap<>();

    public static AbstractRegisterLoginComponent getComponent(String type) {
        AbstractRegisterLoginComponent component = componentMap.get("type");
        if (component == null) {
            synchronized (componentMap) {
                component = componentMap.get(type);
                if (component == null) {
                    component = new RegisterLoginComponent(funcMap.get(type));
                    componentMap.put(type, component);
                }
            }
        }
        return component;
    }
}
