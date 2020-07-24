package dev.proj.tl.concurrent;


import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.IntStream;

public class ReentrantLockTest implements Runnable {
    private ReentrantLock reentrantLock = new ReentrantLock();


    public static void main(String[] args) {
        ReentrantLockTest reentrantLockTest = new ReentrantLockTest();
        IntStream.range(0, 2).forEach(i -> {
            new Thread(reentrantLockTest).start();
        });
    }

    @Override
    public void run() {
        try {
            reentrantLock.lock();
        } finally {
            reentrantLock.unlock();
        }
    }
}
