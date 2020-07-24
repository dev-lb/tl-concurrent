package dev.proj.tl.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConditionTest {
    private Lock lock = new ReentrantLock();
    private Condition full = lock.newCondition();
    private Condition empty = lock.newCondition();
    private int i = 0;

    public static void main(String[] args) {
        ConditionTest conditionTest = new ConditionTest();
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    conditionTest.produce();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    conditionTest.consume();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        executorService.shutdown();
    }

    public void produce() throws InterruptedException {
        lock.lock();
        //System.out.println("begin produce");
        try {
            for (; ; ) {
                while (i >= 30) {
                    TimeUnit.SECONDS.sleep(3);
                    full.await();
                }
                i++;
                System.out.println("produce " + i);
                empty.signal();
            }
        } finally {
            lock.unlock();
        }
    }

    public void consume() throws InterruptedException {
        lock.lock();
        //System.out.println("begin consume");
        try {
            for (; ; ) {
                while (i <= 0) {
                    TimeUnit.SECONDS.sleep(3);
                    empty.await();
                }
                i--;
                System.out.println("consume " + i);
                full.signal();
            }
        } finally {
            lock.unlock();
        }
    }

}
