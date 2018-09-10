package com.thread.architect.waitAndNotify.base;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 采用wait / notify 实现，一定要配合synchronized使用
 * 8
 * 9
 * 10
 * Exception in thread "t2" java.lang.RuntimeException
 * t2  ,stop
 *
 * notify不释放锁
 *    //由于 wait方法释放锁，notify方法不释放锁，所以
 *         //如果t1在前，则t2根本不能收到通知
 *         //反之，如果t2先执行，wait后，会释放锁，所以能够执行停止
 *         //但是美中不足的是，t1执行到5后，还是会继续向下执行，在数据量大时等业务场景下，可能造成问题
 *         //比如t1线程是在大数据量中 获取一个结果，只要找到就通知t2，则t1应该不再继续向下找。
 *         //不应该是每次找完数据，才通知t2
 *
 */
public class SynWithWaitAndNotify2{
    private List list = new ArrayList<>();


    public int size(){
        return list.size();
    }

    public void add(Object obj){
        list.add(obj);
    }


    static class Thread1 implements Runnable{
        private  SynWithWaitAndNotify2 synWithWaitAndNotify2;
        private  Object lock;

        public Thread1(SynWithWaitAndNotify2 synWithWaitAndNotify2,Object lock){
            this.synWithWaitAndNotify2 = synWithWaitAndNotify2;
            this.lock = lock;
        }

        @Override
        public void run() {
            synchronized (lock){
                for(int i = 0;i<=10;i++){
                    synWithWaitAndNotify2.add(i);

                    try {
                        TimeUnit.SECONDS.sleep(3);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(i);
                    if(synWithWaitAndNotify2.size() == 5){
                        System.out.println("已经发出通知..");
                        lock.notify();
                    }
                    //当notify后 业务逻辑继续，锁仍没有释放
                }
            }
        }
    }

    static class Thread2 implements Runnable{

        private  SynWithWaitAndNotify2 synWithWaitAndNotify2;
        private  Object lock;

        public Thread2(SynWithWaitAndNotify2 synWithWaitAndNotify2,Object lock){
            this.synWithWaitAndNotify2 = synWithWaitAndNotify2;
            this.lock = lock;
        }
        @Override
        public void run() {
            synchronized (lock) {
                if (synWithWaitAndNotify2.size() != 5) {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println(Thread.currentThread().getName() + "  ,stop");
                throw new RuntimeException();
            }
        }
    }

    public static void main(String[] args) {
        SynWithWaitAndNotify2 synWithWaitAndNotify = new SynWithWaitAndNotify2();
        Object lock = new Object();


        //线程t1： 添加元素
        Thread1 thread1 = new Thread1(synWithWaitAndNotify,lock);

        //线程t2,当线程t1添加元素达到5个时,停止线程2。
        Thread2 thread2 = new Thread2(synWithWaitAndNotify,lock);

        Thread t1 = new Thread(thread1,"t1");
        Thread t2 = new Thread(thread2,"t2");

        //由于 wait方法释放锁，notify方法不释放锁，所以
        //如果t1在前，则t2根本不能收到通知
        //反之，如果t2先执行，wait后，会释放锁，所以能够执行停止。

        //但是美中不足的是，t1执行到5后，还是会继续向下执行，在数据量大时等业务场景下，可能造成问题
        //比如t1线程是在大数据量中 获取一个结果，只要找到就通知t2，则t1应该不再继续向下找。
        //不应该是每次找完数据，才通知t2
        t2.start();
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        t1.start();



    }

}

