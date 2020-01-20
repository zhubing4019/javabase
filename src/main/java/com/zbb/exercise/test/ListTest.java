package com.zbb.exercise.test;

import lombok.Data;
import org.junit.Test;

import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class ListTest {

    private final static String NAME = "";

    public static String getNAME() {
        return NAME;
    }

    /**
     * modCount 设置的原因? 不设置不行吗?
     */
    @Test
    public void linkTest() {
        ArrayList<Integer> arrayList = new ArrayList<>();
        arrayList.iterator();
        arrayList.sort(null);
        /*System.out.println(java.security.AccessController.doPrivileged(
                new sun.security.action.GetBooleanAction(
                        "java.util.Arrays.useLegacyMergeSort")).booleanValue());*/

        //并发 copy and write, 占用内存，导致频繁FLLGC
        CopyOnWriteArrayList<Integer> copyOnWriteArrayList = new CopyOnWriteArrayList<Integer>();
        copyOnWriteArrayList.iterator();
        copyOnWriteArrayList.add(111);
        Integer integer = copyOnWriteArrayList.get(1);
        System.out.println(integer);
        //synchronized - 代码块
        List<Integer> integers = Collections.synchronizedList(arrayList);
        //Vector
        Vector vector = new Vector();
        vector.add(11);
        vector.size();


        LinkedList list = new LinkedList();
        list.add(1);
        list.add(1);
        list.add(1);
        list.get(1);
        list.iterator();
        list.get(0);
        ListIterator listIterator = list.listIterator(0);
        boolean flag = listIterator.hasNext();
        Arrays.parallelSort(new int[10]);
        Arrays.sort(new int[10]);
        Collections.sort(null);
        Arrays.parallelSort(new int[10]);
    }

    @Test
    public void ssss() {
        /**
         *   Deque/ArrayDeque
         */
    }


    /**
     * 并发修改异常ConcurrentModificationException
     * CopyOnWriteList 遍历不会以后异常，因为持有old_array,数据实时性、内存有问题！保证最终一致性，cas 必须加锁  ReentrantLock
     *
     * @throws InterruptedException
     */
    @Test
    public void testListConcurrent() throws InterruptedException {
        List<Integer> list = Collections.synchronizedList(new ArrayList(100));

        list.add(500);
        list.add(2000);
        list.iterator();

        new Thread(new Runnable() {
            public void run() {
                Iterator i = list.iterator(); // Must be in synchronized block
                while (i.hasNext()) {
                    try {
                        System.out.println("get");
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(i.next());
                }
            }
        }, "t1").start();

        new Thread(new Runnable() {
            public void run() {
                System.out.println();
                list.remove(0);
                System.out.println("remove:" + list.size());
            }
        }, "t2").start();
        Thread.sleep(2000);
        System.out.println(Arrays.toString(list.toArray()));

    }

    @Test
    public void queueTest() throws InterruptedException {
        //不自动扩容
//        ArrayQueue<Integer> queue = new ArrayQueue<>(1);
//        queue.add(11);
//        queue.remove(0);
//        queue.add(11);
//
//        for (int i = 0; i < 11; i++) {
//            queue.add(11);
//        }

        //阻塞队列： 添加不可为空， add/offer/put  remove/poll/take  element/peek 1.异常 2.null或false 3.阻塞
        SynchronousQueue<Integer> synchronousQueue = new SynchronousQueue<>();
        System.out.println("获取数据...等待中");
//        Integer poll = synchronousQueue.poll(5, TimeUnit.SECONDS);
        Integer poll00 = synchronousQueue.poll();
        System.out.println("获取数据:" + poll00);
        Integer take = synchronousQueue.take();
        System.out.println("获取数据:" + take);
        boolean offer = synchronousQueue.offer(11);
        System.out.println("新增数据:" + offer);
        synchronousQueue.put(11);

        LinkedBlockingQueue linkedBlockingQueue = new LinkedBlockingQueue();
        linkedBlockingQueue.put(1);
        linkedBlockingQueue.take();

        ArrayBlockingQueue arrayBlockingQueue = new ArrayBlockingQueue(100);
        arrayBlockingQueue.add(1);
        arrayBlockingQueue.offer(1);
        arrayBlockingQueue.put(1);
        arrayBlockingQueue.take();
        arrayBlockingQueue.remove();
        arrayBlockingQueue.peek();
        arrayBlockingQueue.element();

        DelayQueue delayQueue = new DelayQueue();

        /**
         * 使用小根堆实现
         */
        PriorityQueue priorityQueue = new PriorityQueue();

        /**
         * 使用无锁模式实现： lock free
         * while + cas
         *
         */
        ConcurrentLinkedQueue concurrentLinkedQueue = new ConcurrentLinkedQueue();
        concurrentLinkedQueue.offer(1);

        AtomicInteger atomicInteger = new AtomicInteger();
        atomicInteger.get();
        atomicInteger.getAndIncrement();
        atomicInteger.getAndIncrement();
    }

    @Test
    public void priorityQueue1111() {
        DelayQueue queue = new DelayQueue();
//        queue.offer(10);
        System.out.println(Arrays.toString(queue.toArray()));
        Iterator iterator = queue.iterator();
        System.out.println();
        int size = queue.size();
        for (int i = 0; i < size; i++) {
            Object poll = queue.poll();
            System.out.println(poll);
        }

    }

    @Data
    class DelayTest implements Delayed {

        private Integer id;
        private long excuteTime;

        public DelayTest(Integer id, long delayTime) {
            this.id = id;
            this.excuteTime = TimeUnit.NANOSECONDS.convert(delayTime, TimeUnit.MILLISECONDS) + System.nanoTime();
        }

        @Override
        public long getDelay(TimeUnit unit) {
            return unit.convert(this.excuteTime - System.nanoTime(), TimeUnit.NANOSECONDS);
        }

        @Override
        public int compareTo(Delayed o) {
            DelayTest delayTest = (DelayTest) o;
            return this.getId().compareTo(delayTest.getId());
        }
    }


    @Test
    public void priorityQueue() {
        PriorityQueue priorityQueue = new PriorityQueue();
        priorityQueue.offer(10);
        priorityQueue.offer(11);
        priorityQueue.offer(2);
        priorityQueue.offer(7);
        priorityQueue.offer(9);
        priorityQueue.offer(12);
        priorityQueue.offer(1);
        System.out.println(Arrays.toString(priorityQueue.toArray()));
        int size = priorityQueue.size();
        for (int i = 0; i < size; i++) {
            Object poll = priorityQueue.poll();
            System.out.print(poll + ", ");
            System.out.println(Arrays.toString(priorityQueue.toArray()));
        }
        priorityQueue.remove(1);

    }

    /**
     * 父节点： left = parent << 1 +1  (k < half  half = n >>> 1;)
     * 子节点： parent = (child -1) >>> 1    (k > 0)
     */
    @Test
    public void heap() {
        int[] arr = {10, 11, 2, 7, 9, 12, 1};
        System.out.println(Arrays.toString(arr));
        //建堆   [1, 7, 2, 11, 9, 12, 10]
        for (int i = 1; i < arr.length; i++) {
            siftUp(i, arr);
        }
        System.out.println(Arrays.toString(arr));

//        拆堆
        for (int i = arr.length - 1; i >= 0; i--) {
            System.out.print(arr[0] + ", ");
            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;
            siftDown(0, arr, i);
        }
        System.out.println();
    }

    /**
     * 建堆
     */
    @Test
    public void heap01() {
        int[] arr = {10, 11, 2, 7, 9, 12, 1};
        System.out.println(Arrays.toString(arr));
        //建堆   [1, 7, 2, 11, 9, 12, 10]
        int half = arr.length/2;
        for (int i = half; i >= 0; i--) {
            siftDown(i, arr, arr.length);
        }
        System.out.println(Arrays.toString(arr));

//        拆堆
        for (int i = arr.length - 1; i >= 0; i--) {
            System.out.print(arr[0] + ", ");
            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;
            siftDown(0, arr, i);
        }
        System.out.println();
    }

    public void siftUp(int k, int[] arr) {
        while (k > 0) {
            int p = (k - 1) >>> 1;
            if (arr[k] >= arr[p]) {//不用交换
                break;
            }
            //交换
            int temp = arr[p];
            arr[p] = arr[k];
            arr[k] = temp;
            k = p;
        }
    }

    // 1
    public void siftDown(int k, int[] arr, int size) {
        int o = arr[k];
        int half = size >>> 1;           // loop while a non-leaf
        while (k < half) {
            int c = k * 2 + 1;
            int right = c + 1;
            if (right < size && arr[c] > arr[c + 1]) {
                c = c + 1;
            }
            if (o <= arr[c]) {
                break;
            }
            //交换
            arr[k] = arr[c];
            k = c;
        }
        arr[k] = o;
    }


    @Test
    public void testsss() {
        ThreadLocal<Integer> ticketNumHolder = new ThreadLocal<Integer>();
        ticketNumHolder.set(1111);
        ticketNumHolder.get();
    }


}
