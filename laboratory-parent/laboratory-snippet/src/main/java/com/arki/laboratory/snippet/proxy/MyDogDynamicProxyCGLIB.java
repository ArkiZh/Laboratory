package com.arki.laboratory.snippet.proxy;

import com.arki.laboratory.common.Logger;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class MyDogDynamicProxyCGLIB implements MethodInterceptor {

    private Object targetObject;

    public Object createProxyObject(Object targetObject) {
        this.targetObject = targetObject;
        Enhancer enhancer = new Enhancer();
        enhancer.setCallback(this);
        enhancer.setSuperclass(targetObject.getClass());
        return enhancer.create();
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        Logger.info("小狗cglib动态代理开始");
        Object result = methodProxy.invoke(targetObject, objects);
        Logger.info("小狗cglib动态代理结束");
        return result;
    }
}
