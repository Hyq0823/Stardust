package com.thread.countDownlatch;

import java.util.concurrent.CountDownLatch;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(2);
         Bo bo = new Bo();


        Worker1 worker1 = new Worker1(bo,countDownLatch);
        Worker2 worker2 = new Worker2(bo,countDownLatch);
        Worker3 worker3 = new Worker3(bo,countDownLatch);

        Thread thread1 = new Thread(worker1,"线程1");
        Thread thread2 = new Thread(worker2,"线程2");
        Thread thread3 = new Thread(worker3,"线程3");


        //线程3启动
        //线程2启动
        //线程1启动

        System.out.println(bo.getResult());
        thread3.start();
        thread1.start();
        thread2.start();

        //countDownlatch: 主线程必须等待map赋值后才能结束
        //join: 主线程必须等到三个线程执行完毕才能结束
        countDownLatch.await();

        System.out.println(bo.getMap());
        System.out.println("主线程结束....");



    }
}
