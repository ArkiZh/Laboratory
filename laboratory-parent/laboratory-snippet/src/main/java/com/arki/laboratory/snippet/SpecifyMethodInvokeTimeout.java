package com.arki.laboratory.snippet;

import com.arki.laboratory.common.Logger;
import com.arki.laboratory.common.Timer;

import java.util.concurrent.*;

public class SpecifyMethodInvokeTimeout {


    public static void main(String[] args) {
        // Prepare the new thread to execute.
        ExecutorService executor = Executors.newSingleThreadExecutor();
        FutureTask<String> futureTask = new FutureTask<>(new Callable<String>() {
            @Override
            public String call() throws Exception {
                // Define the new command to call.
                return specifySleepTime(1000);
            }
        });
        // Start to execute the call method in the new thread.
        executor.execute(futureTask);
        try {
            Timer timer = new Timer();
            timer.start();
            // Fetch the result of execution with timeout 1500ms.
            String result = futureTask.get(1500, TimeUnit.MILLISECONDS);
            timer.stop();
            Logger.info("Elapsed time: [{}]ms. Executed result: [{}]", timer.elapsedTime()*1000, result);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }finally {
            futureTask.cancel(true);
            executor.shutdown();
        }
    }

    public static String specifySleepTime(long sleepTimeInMillisecond) {
        try {
            Logger.info("Sleep start. {}ms", sleepTimeInMillisecond);
            Thread.sleep(sleepTimeInMillisecond);
            Logger.info("Sleep end. {}ms", sleepTimeInMillisecond);
            return "Sleep " + sleepTimeInMillisecond + "ms succeed.";
        } catch (InterruptedException e) {
            Logger.info("Sleep " + sleepTimeInMillisecond + "ms error.");
            e.printStackTrace();
            return "Sleep " + sleepTimeInMillisecond + "ms error.";
        }
    }
}
