package com.thread.architect.base.lesson2_multiThreadSyn;


import java.util.concurrent.TimeUnit;

/**
 *  synchronized 锁住的不是一段代码
 * 多个线程对象 锁,那个线程先执行synchronized方法，那个线程先获取到锁
 *
 * 1） 同一个对象时，，多个线程，不加锁，可能出现脏读
 *   money is : 300
 *  money is : 300
 *
 *  2）同一个对象，多个线程，加锁，业务正常
 *   money is : 100
 *  money is : 300
 *
 *  3）不同对象，多个线程,加锁，不是同一把锁
 */
public class MultiThreadSyn{
    /**
     * 使用lock和在方法上加synchronized效果是一样的，都是对象锁，同一个对象就是同一把，不同对象锁不同
     */
    private Object lock = new Object();



    private  int money;
    public    void  make(String way){

        synchronized (lock) {
            if ("a".equals(way)) {
                money = 100;
                try {
                    TimeUnit.SECONDS.sleep(4);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            if ("b".equals(way)) {
                money = 300;
            }
            System.out.println(Thread.currentThread().getName() + ",lock :  "+lock+",money is : " + money);
        }
    }


    public static void main(String[] args) {

        //实例化一个对象时，锁是同一把
        //t1,lock :  java.lang.Object@278e4dc4,money is : 100
        //t2,lock :  java.lang.Object@278e4dc4,money is : 300
//        MultiThreadSyn multiThreadSyn = new MultiThreadSyn();
//        new Thread(()-> multiThreadSyn.make("a"),"t1").start();
//        new Thread(()->multiThreadSyn.make("b"),"t2").start();


        //实例化多个对象时，锁不是同一把
        //t2,lock :  java.lang.Object@2e89f61,money is : 300
        //t1,lock :  java.lang.Object@76ee89dd,money is : 100
        MultiThreadSyn multiThreadSynGroup1 = new MultiThreadSyn();
        MultiThreadSyn multiThreadSynGroup2 = new MultiThreadSyn();
        new Thread(()-> multiThreadSynGroup1.make("a"),"t1").start();
        new Thread(()->multiThreadSynGroup2.make("b"),"t2").start();

    }
}
