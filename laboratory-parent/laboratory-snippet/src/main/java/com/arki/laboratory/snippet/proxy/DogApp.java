package com.arki.laboratory.snippet.proxy;

public class DogApp {
    public static void main(String[] args) {
        //静态代理
        MyDog myDog = new MyDog();
        MyDogStaticProxy myDogStaticProxy = new MyDogStaticProxy(myDog);
        myDogStaticProxy.bark();
        myDogStaticProxy.watchDoor();

        //jdk动态代理，要求有接口
        BaseDog myDogDynamicProxyJDK = (BaseDog)new MyDogDynamicProxyJDK().createProxyObject(new MyDog());
        myDogDynamicProxyJDK.bark();
        myDogDynamicProxyJDK.watchDoor();

        //cglib动态代理，直接作用在类上面，不需要接口
        MyDog myDogDynamicProxyCGLIB = (MyDog) new MyDogDynamicProxyCGLIB().createProxyObject(new MyDog());
        myDogDynamicProxyCGLIB.bark();
        myDogDynamicProxyCGLIB.watchDoor();
    }
}
