package dev.proj.tl.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 读写锁 读读不互斥 读写互斥 写写互斥
 */
public class ReentrantReadWriteLockTest {

    private ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock();

    public static void main(String[] args) {
        ReentrantReadWriteLockTest reentrantReadWriteLockTest = new ReentrantReadWriteLockTest();
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    reentrantReadWriteLockTest.read();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    reentrantReadWriteLockTest.read();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    reentrantReadWriteLockTest.write();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        executorService.shutdown();
    }

    public void read() throws InterruptedException {
        reentrantReadWriteLock.readLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + " hold read lock!");
            Thread.sleep(3000);
            System.out.println(Thread.currentThread().getName() + " release read lock!");
        } finally {
            reentrantReadWriteLock.readLock().unlock();
        }
    }

    public void write() throws InterruptedException {
        reentrantReadWriteLock.writeLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + " hold write lock!");
            Thread.sleep(3000);
            System.out.println(Thread.currentThread().getName() + " release write lock!");
        } finally {
            reentrantReadWriteLock.writeLock().unlock();
        }
    }

}
