package com.zbb.exercise.test.thread;

import org.junit.Test;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * 生产与消费 - 阻塞队列
 */
public class TreadCusProTest {
    private int size = 10;
    private Queue queue = new ArrayDeque(size);

    @Test
    public void doing() throws InterruptedException {
        Productor p1 = new Productor();
        p1.setDaemon(true);
        p1.start();
//        new Productor().start();
        Customer c1 = new Customer();
        c1.setDaemon(true);
        c1.start();
        Thread.sleep(10000);
    }

    public class Customer extends Thread {
        @Override
        public void run() {
            while (true) {
                synchronized (queue) {
                    while (queue.size() == 0) {
                        try {
                            System.out.println("消费完了，等待");
                            queue.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    System.out.println(queue.remove(0));
                    queue.notifyAll();
                }
            }
        }
    }

    public class Productor extends Thread {
        @Override
        public void run() {
            while (true) {
                synchronized (queue) {
                    while (queue.size() == size) {
                        try {
                            System.out.println("生产满了，等待");
                            queue.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    System.out.println(10);
                    queue.add(10);
                    queue.notifyAll();
                }
            }
        }
    }

}
