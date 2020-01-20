package com.zbb.exercise.test.lock;

import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

/**
 *  CHL
 *  基于链表通过循环cas，实现无阻塞前驱锁
 *  隐式链表：循环前驱节点
 */
public class CLHLock {

    public static class CLHNode {
        private volatile boolean isLocked = true;
    }
    //尾部节点
    private volatile CLHNode tail;
    //线程持有的节点
    private static final ThreadLocal<CLHNode> local = new ThreadLocal<CLHNode>();
    //cas 更新信息
    private static final AtomicReferenceFieldUpdater<CLHLock, CLHNode> fieldUpdater = AtomicReferenceFieldUpdater.newUpdater(CLHLock.class, CLHNode.class, "tail");

    public void lock(){
        System.out.println(Thread.currentThread().getName() + "尝试获取锁。。。");
        //创建并保存 Node
        CLHNode node = new CLHNode();
        local.set(node);
        //获取前驱并设置当前节点 getset
        CLHNode preNode = fieldUpdater.getAndSet(this, node);
        //检测前驱 是否锁定
        if(preNode != null){
            while(preNode.isLocked){

            }
        }
    }

    public void unlock(){
        //获取线程 node
        CLHNode node = local.get();
        //cas 通过node设置tail节点为null，成功：是尾锁；失败：不是尾锁，设置isLocked=false，下一节点进入锁
        if(!fieldUpdater.compareAndSet(this, node, null)){
            node.isLocked = false;
        }
    }

}
