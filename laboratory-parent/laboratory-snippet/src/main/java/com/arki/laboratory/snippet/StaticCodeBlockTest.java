package com.arki.laboratory.snippet;

import com.arki.laboratory.common.Logger;

public class StaticCodeBlockTest {

    static {
        Logger.info("Static code block executed.");
    }

    {
        Logger.info("Code block executed.");
    }

    public static void main(String[] args) {
        sayHi();
        StaticCodeBlockTest staticCodeBlockTest = new StaticCodeBlockTest();
    }

    public static void sayHi() {
        Logger.info("Hi!");
    }

}
