package com.arki.laboratory.snippet.thread;

import java.util.concurrent.Callable;

public class CallableTest implements Callable<String> {
    private static int count;
    @Override
    public String call() throws Exception {
        String s = "CallableTest count:" + count++;
        System.out.println(s);
        return "Result of " + s;
    }
}
