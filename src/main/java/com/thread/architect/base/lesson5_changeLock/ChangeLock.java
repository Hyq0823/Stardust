package com.thread.architect.base.lesson5_changeLock;

import java.util.concurrent.TimeUnit;

/**
 * 改变锁
 *
 *
 *
 * 当前线程: t1
 * 当前线程: t2
 * 结束： t1
 * 结束： t2
 *
 * 可以看到加了锁，但是并不像预期的，t1执行完后，才执行t2
 * 1)因为在method内部，动态改变了锁，第二次调用的synchronized，持有的锁和原来不是同一把锁，所以第二个线程，能立马开始执行
 *
 * 2)使用synchronized代码块加锁,比较灵活
 */
public class ChangeLock {

    private String lock = "i'm lock";


    public void method(){

        synchronized(lock){
            System.out.println("当前线程: "  + Thread.currentThread().getName());


            //在方法内部改变锁
            lock = "change lock";


            try {
                TimeUnit.SECONDS.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("结束： " + Thread.currentThread().getName());
        }
    }


    public static void main(String[] args) {


        ChangeLock changeLock = new ChangeLock();


        new Thread(()->{
            changeLock.method();
        },"t1").start();


        try {
            TimeUnit.SECONDS.sleep(4);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(()->{
            changeLock.method();
        },"t2").start();

    }
}
