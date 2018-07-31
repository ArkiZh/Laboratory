package com.arki.laboratory.snippet.thread;


import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class AsynchronizeTaskTest {

    private static Map<String, Object> taskListInfoMap = new HashMap<>();
    private static ExecutorService executorService = Executors.newCachedThreadPool();

    private enum STATUS{
        RUNNING,CACHED
    }

    public static void main(String[] args) {
        //1: input from 0 to 9, eternal loop
        //2: each task sleep 2 second, then cache the result into thread local with timestamp for 8 seconds
        //3: when the same task come again:
        //   if the former one is still running, return at once without result
        //   if the former one is finished and cached, return the result,
        //      then start a new thead to fetch the latest result
        //   if the former one is finished and the cached result is expired,
        //      start a new thread for this input, then return without result
        int input = 0;
        while (true) {
            final int taskInput = input;

            // Judge status
            HashMap<String, Object> currentTaskInfo = (HashMap<String, Object>) taskListInfoMap.get(String.valueOf(taskInput));
            if (currentTaskInfo == null) {
                System.out.println("Task for input " + taskInput + " : haven't run, start now.");
                fetchResult(taskInput);
            } else if (STATUS.RUNNING.equals(currentTaskInfo.get("status"))) {
                if (currentTaskInfo.containsKey("timestamp")) {
                    // Judge whether the result is expired.
                    if (System.currentTimeMillis() - (long) currentTaskInfo.get("timestamp") > 8000) {
                        System.out.println("Task for input " + taskInput + " : result expired, fetching...");
                    } else {
                        // Not expired, return result.
                        System.out.println("Result for input " + taskInput + " : " + currentTaskInfo.get("result")+" ,refreshing...");
                    }
                } else {
                    System.out.println("Task for input " + taskInput + " : running...");
                }

            } else {
                // Judge whether the result is expired.
                if (System.currentTimeMillis() - (long) currentTaskInfo.get("timestamp") > 8000) {
                    System.out.println("Task for input " + taskInput + " : result expired, fetch now.");
                } else {
                    // Not expired, return result.
                    System.out.println("Result for input " + taskInput + " : " + currentTaskInfo.get("result")+" ,refresh now.");
                }
                fetchResult(taskInput);
            }

            /*try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }*/

            if(input==9) input=0;
            else input++;
        }
    }

    private static void fetchResult(final int taskInput) {
        // Fetch result
        Thread taskThread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Current input: " + taskInput + " Thread running: " + Thread.currentThread().getName());

                HashMap<String, Object> taskInfoMap;
                if (taskListInfoMap.containsKey(String.valueOf(taskInput))) {
                    taskInfoMap = (HashMap<String, Object>) taskListInfoMap.get(String.valueOf(taskInput));
                }else{
                    taskInfoMap = new HashMap<>();
                    taskListInfoMap.put(String.valueOf(taskInput), taskInfoMap);
                }
                taskInfoMap.put("status", STATUS.RUNNING);
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                taskInfoMap.put("timestamp", System.currentTimeMillis());
                taskInfoMap.put("result", "Result From input " + taskInput);
                taskInfoMap.put("status", STATUS.CACHED);
                //taskListInfoMap.put(String.valueOf(taskInput), taskInfoMap);

                System.out.println("Current input: " + taskInput + " Thread finished: " + Thread.currentThread().getName());
            }
        });
        //taskThread.start();
        // using thread pool
        executorService.execute(taskThread);
        System.out.println("Pool size: " + ((ThreadPoolExecutor) executorService).getPoolSize());
    }
}
