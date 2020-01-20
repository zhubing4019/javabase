package com.zbb.exercise.test.lock;

import org.junit.Test;

import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockTest {

    @Test
    public void sss() {
        ReentrantLock lock = new ReentrantLock(true);
        System.out.println(lock.getHoldCount());
        //state!=0
        System.out.println(lock.isLocked());
        lock.lock();
        System.out.println(lock.getHoldCount());
        System.out.println(lock.isLocked());
        try {
            lock.lock();
            System.out.println(lock.getHoldCount());
            System.out.println("进入锁");
            lock.unlock();
            System.out.println(lock.getHoldCount());
        } finally {
            lock.unlock();
        }
    }

}
