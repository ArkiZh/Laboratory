package com.arki.laboratory.snippet.proxy;

import com.arki.laboratory.common.Logger;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class MyDogDynamicProxyJDK implements InvocationHandler {

    private Object targetObject;

    public Object createProxyObject(Object targetObject) {
        this.targetObject = targetObject;
        return Proxy.newProxyInstance(targetObject.getClass().getClassLoader(),
                targetObject.getClass().getInterfaces(),
                this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Logger.info("小狗jdk动态代理开始");
        Object result = method.invoke(targetObject, args);
        Logger.info("小狗jdk动态代理结束");
        return result;
    }
}
