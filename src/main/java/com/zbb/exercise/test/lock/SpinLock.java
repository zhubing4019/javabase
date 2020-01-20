package com.zbb.exercise.test.lock;

import java.util.concurrent.atomic.AtomicReference;

/**
 * 自旋锁
 * 不可冲入
 * 非公平锁
 */
public class SpinLock {

    private volatile Thread currentThread;

    private static final AtomicReference<Thread> reference = new AtomicReference<Thread>();

    public void lock(){
        Thread thread = Thread.currentThread();
        while (!reference.compareAndSet(null, thread)){

        }
    }

    public void unlock(){
        Thread thread = Thread.currentThread();
        reference.compareAndSet(thread, null);
    }
}
