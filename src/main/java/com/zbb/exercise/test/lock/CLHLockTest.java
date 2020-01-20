package com.zbb.exercise.test.lock;

import org.junit.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantLock;

public class CLHLockTest {

    private final CLHLock lock = new CLHLock();
    private final ReentrantLock unfairLock = new ReentrantLock();

    @Test
    public void test111() throws InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < 5 ; i++) {
            executorService.submit(new CallTest());
        }
        Thread.sleep(10000);
        executorService.shutdown();
    }

    @Test
    public void unfairLock() throws Exception {
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < 5 ; i++) {
            Thread.sleep(1);
            executorService.submit(new UnFairCallTest());
        }
        Thread.sleep(10000);
        executorService.shutdown();
    }

    class CallTest extends Thread {
        @Override
        public void run() {
            lock.lock();
            try {
                System.out.println(Thread.currentThread().getName() + "获取锁");
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }

    class UnFairCallTest extends Thread {
        @Override
        public void run() {
            unfairLock.lock();
            try {
                System.out.println(Thread.currentThread().getName() + "获取锁");
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                unfairLock.unlock();
            }
        }
    }


}
