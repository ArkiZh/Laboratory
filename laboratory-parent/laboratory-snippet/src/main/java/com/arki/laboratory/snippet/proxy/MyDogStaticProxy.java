package com.arki.laboratory.snippet.proxy;

import com.arki.laboratory.common.Logger;

public class MyDogStaticProxy {

    private MyDog myDog;

    public MyDogStaticProxy(MyDog myDog) {
        this.myDog = myDog;
    }

    public void bark() {
        Logger.info("狗叫静态代理开始");
        this.myDog.bark();
        Logger.info("狗叫静态代理结束");
    }

    public void watchDoor() {
        Logger.info("看门静态代理开始");
        this.myDog.watchDoor();
        Logger.info("看门静态代理结束");
    }

}
