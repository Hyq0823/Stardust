package com.thread.architect.base.lesson3_objSynAndAsynCall;

import java.util.concurrent.TimeUnit;

/**
 *
 * 对象锁 同步和异步的问题
 * 1) 同一个对象，其他线程能立马调用到未加synchronized的方法，调用synchronized方法则会阻塞（无论是不是同一个方法，只要是其关键字修饰的）
 * @see #main(String[])
 *
 *
 * 2)可重入锁，一个使用synchronized的方法中，可以synchronized修饰的其他方法，不用再去获取锁，这叫锁的可重入 {@link #methodSyn3()}
 * @see #methodSyn3()
 */
public class ObjSynAndAsynCallDemo {
    public synchronized  void methodSyn(){
        System.out.println("我这个方法加了同步，当前："+Thread.currentThread().getName());
        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public synchronized  void methodSyn2(){
        System.out.println("同步2，当前："+Thread.currentThread().getName());
    }

    public synchronized void methodSyn3(){
        System.out.println("可重入锁测试,同步3：" + Thread.currentThread().getName());
        methodSyn2();
        methodSyn();
    }

    public void methodAsync(){
        System.out.println("我是没有加同步限制的方法，当前： "+Thread.currentThread().getName());
    }


    public static void main(String[] args) {

        ObjSynAndAsynCallDemo obj = new ObjSynAndAsynCallDemo();

        new Thread(()->{
//            obj.methodSyn();
            obj.methodSyn3();
        },"t1").start();


        new Thread(()->{

            //obj.methodAsync();
//            obj.methodSyn2();
            //上面两种调用都会阻塞，因为锁是同一把


            //调用当前对象的非同步方法，则不会阻塞
//            obj.methodSyn();
        },"t2").start();



    }

}
