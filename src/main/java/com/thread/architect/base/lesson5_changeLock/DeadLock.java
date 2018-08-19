package com.thread.architect.base.lesson5_changeLock;

import java.util.concurrent.TimeUnit;

/**
 * 死锁
 * 实现思路： 两个线程，
 * 1个线程需要锁住对象a，然后锁住对象b
 * 另一个线程需要锁住对象b,然后锁住对象a
 *
 * 在执行完第一步操作后，分别拿a/b锁，但是彼此又互相持有 b、a锁
 * 线程都等着对方解锁，都进入等待，永远不会执行
 *
 *
 * 可以看出，只要尽量避免加多个锁，就可以尽量避免死锁。
 */
public class DeadLock {

//    private Object lock1 = new Object();
//    private Object lock2 = new Object();

    private static Object lock2 = new Object();
    private static Object lock1 = new Object();


    public void method(String path){
        if("a".equals(path)){
            synchronized (lock1){
                try {
                    TimeUnit.SECONDS.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() +  ", lock1 execute");
            }

            synchronized (lock2){
                System.out.println(Thread.currentThread().getName() +  ", lock2 execute");
            }
        }


        if("b".equals(path)){
            synchronized (lock2){

                try {
                    TimeUnit.SECONDS.sleep(15);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() +  ", lock22 execute");
            }

            synchronized (lock1){
                System.out.println(Thread.currentThread().getName() +  ", lock11 execute");
            }
        }
    }


    public static void main(String[] args) {
        DeadLock deadLock = new DeadLock();


        DeadLock deadLock2 = new DeadLock();

        new Thread(()->deadLock.method("a"),"t1").start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(()->deadLock2.method("b"),"t2").start();
    }
}
