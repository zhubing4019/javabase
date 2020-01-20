package com.zbb.exercise.test.thread;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.concurrent.*;

@Slf4j
public class TreadTest {

    @Test
    public void tt() throws ExecutionException, InterruptedException {
        Thread thread = new Thread();
        thread.run();
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(10, 10, 60l, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<>(10), new ThreadPoolExecutor.AbortPolicy());
        FutureTask<String> futureTask = new FutureTask<String>(new TestCallable());
        Future submit = threadPoolExecutor.submit(new TestCallable());

        System.out.println(submit.get());
//        System.out.println(submit.cancel(Boolean.FALSE));
        System.out.println(submit.isDone());
        System.out.println(Thread.currentThread().getName()+"主线程结束");

        threadPoolExecutor.shutdown();
        threadPoolExecutor.shutdownNow();

        Executors.newCachedThreadPool();
        Executors.newFixedThreadPool(10);
        Executors.newSingleThreadExecutor();
        Executors.newScheduledThreadPool(10);
        Executors.newWorkStealingPool();
    }


    public class TestCallable implements Callable<String> {
        @Override
        public String call() throws Exception {
            System.out.println(Thread.currentThread().getPriority());
            System.out.println(Thread.currentThread().getThreadGroup().getName());
            System.out.println(Thread.currentThread().getName()+"，执行中");
            Thread.sleep(1*1000);
            System.out.println(Thread.currentThread().getName()+"，执行结束");
            return "执行结束";
        }
    }

    private volatile String msg = "初始化";

    @Test
    public  void test() throws InterruptedException, ExecutionException {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(10, 10, 60l, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<>(10), new ThreadPoolExecutor.AbortPolicy());
        Future<String> submit = threadPoolExecutor.submit(new TestCallable001());

//        Thread.sleep(1*5000);
        threadPoolExecutor.shutdown();
        threadPoolExecutor.shutdownNow();
        System.out.println("geting .....");
        System.out.println(submit.get());
        System.out.println("主线程-结束|||"+msg);
    }

    public class TestCallable001 implements Callable<String> {
        @Override
        public String call() throws Exception {
            try {
                Thread.sleep(1*10000);
            } catch (InterruptedException e) {
                msg = "终端异常--执行结束";
                log.error("终端异常--执行结束",e);
                return msg;
            }
            System.out.println(Thread.currentThread().getName()+"，执行结束");
            return "执行结束";
        }
    }


}
