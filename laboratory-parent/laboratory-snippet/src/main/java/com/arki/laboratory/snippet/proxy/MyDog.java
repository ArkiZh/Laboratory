package com.arki.laboratory.snippet.proxy;

import com.arki.laboratory.common.Logger;

public class MyDog implements BaseDog {
    @Override
    public void bark() {
        Logger.info("汪汪汪！");
    }

    @Override
    public void watchDoor() {
        Logger.info("不许过来！");
    }
}
