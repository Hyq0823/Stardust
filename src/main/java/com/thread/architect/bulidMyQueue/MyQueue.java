package com.thread.architect.bulidMyQueue;

import java.util.LinkedList;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author: huangyunquan
 * @Description:Created on 2018/8/23 11:32.
 */
public class MyQueue {
    private LinkedList<Object> list = new LinkedList<>();
    private final int minSize = 0;
    private final int maxSize;
    private AtomicInteger count = new AtomicInteger(0);
    private Object lock = new Object();

    public MyQueue(int maxSize){
        this.maxSize = maxSize;
    }

    public Object take(){
        String threadName = Thread.currentThread().getName();
        Object ret;
        synchronized (lock){

            //为什么这里要用while 用if会怎么样???

            if(list.size() == minSize){
                //容器达到最低容量,不能在向外提供数据,等待填充
                System.out.println("达到最低容量,不能再提供数据,等待...,"+threadName);
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            ret = list.removeFirst();
            count.decrementAndGet();
            //是否需要通知
            lock.notify();
        }
        System.out.println("remove element," + ret+" ,count:" + count+" ,"+threadName);
        return ret;
    }

    public void put(Object obj){
        String threadName = Thread.currentThread().getName();
        synchronized(lock){
            if(list.size() == maxSize){
                System.out.println("容器满了,"+ threadName);
                //容器满，需要阻塞等待
                try {
                    //wait是会立马释放锁的
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            list.addLast(obj);
            count.incrementAndGet();
            //不通知,通知的作用？
            lock.notify(); //  notify不释放锁
            System.out.println("add element," + obj+" ,count:" + count +"   " +threadName);
        }
    }





    public static void main(String[] args) {
        MyQueue myQueue = new MyQueue(5);
        myQueue.put("a");
        myQueue.put("b");
        myQueue.put("c");
        myQueue.put("d");
        myQueue.put("e");

        new Thread(()->{
            myQueue.put("a1");
        },"t1").start();

        new Thread(()->{
            myQueue.take();
        },"t22").start();
        new Thread(()->{
            myQueue.take();
        },"t33").start();


        new Thread(()->{
            myQueue.put("b1");
        },"t2").start();
        new Thread(()->{
            myQueue.put("c1");
        },"t3").start();

        new Thread(()->{
            myQueue.take();
            myQueue.take();
            myQueue.take();
            myQueue.take();
            myQueue.take();
            myQueue.take();
            myQueue.take();
            myQueue.take();
        },"t11").start();


        new Thread(()->{
            myQueue.take();
        },"t44").start();
//        try {
////            TimeUnit.MINUTES.sleep(50);
////        } catch (InterruptedException e) {
////            e.printStackTrace();
////        }
    }
}
