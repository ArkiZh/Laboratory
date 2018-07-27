package com.arki.laboratory.snippet.thread;

public class ThreadTest extends Thread {
    private static int count = 0;
    @Override
    public void run() {
        System.out.println("ThreadTest count: " + count++);
    }
}
