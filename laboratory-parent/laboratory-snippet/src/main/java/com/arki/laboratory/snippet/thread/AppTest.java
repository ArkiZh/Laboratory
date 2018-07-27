package com.arki.laboratory.snippet.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

public class AppTest {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        threadTest();
        runnableTest();
        callableTest();
        threadPoolTest();
    }
    private static void threadPoolTest() {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 10; i++) {
            executorService.execute(new ThreadTest());
        }
    }

    private static void callableTest() throws ExecutionException, InterruptedException {
        //Prepare
        CallableTest callableTest = new CallableTest();
        List<FutureTask> taskList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            taskList.add(new FutureTask(callableTest));
        }
        //Start
        for (int i = 0; i < taskList.size(); i++) {
            new Thread(taskList.get(i)).start();
        }
        //Get result
        for (int i = 0; i < taskList.size(); i++) {
            Object o = taskList.get(i).get();
            System.out.println(o);
        }
    }

    private static void threadTest() throws InterruptedException {
        // 不同线程可共享静态成员变量
        ThreadTest threadTest = new ThreadTest();
        threadTest.start();
        Thread.sleep(10);
        ThreadTest threadTest1 = new ThreadTest();
        threadTest1.start();
    }

    private static void runnableTest() throws InterruptedException {
        // Different thread share the same runnable instance.
        // 不同线程此时可共享成员变量
        RunnableTest runnableTest = new RunnableTest();
        Thread thread = new Thread(runnableTest);
        thread.start();
        Thread.sleep(10);
        Thread thread1 = new Thread(runnableTest);
        thread1.start();
        Thread.sleep(10);
        Thread thread2 = new Thread(runnableTest);
        thread2.start();

        // Each thread has its own runnable instance.
        RunnableTest runnableTest1 = new RunnableTest();
        Thread thread3 = new Thread(runnableTest1);
        thread3.start();
    }
}
