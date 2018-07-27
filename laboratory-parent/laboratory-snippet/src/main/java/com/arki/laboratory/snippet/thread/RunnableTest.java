package com.arki.laboratory.snippet.thread;

public class RunnableTest implements Runnable {
    private int count = 0;

    @Override
    public void run() {
        System.out.println("RunnableTest count:" + count++);
    }
}
