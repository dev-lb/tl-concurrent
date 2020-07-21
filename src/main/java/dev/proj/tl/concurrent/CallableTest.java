package dev.proj.tl.concurrent;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class CallableTest implements Callable<String>{
    @Override
    public String call() throws Exception {
        System.out.println(1/0);
        return null;
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask<String> futureTask = new FutureTask<>(new CallableTest());
        new Thread(futureTask).start();
        futureTask.get();
        System.out.println("main thread run!");
    }
}
