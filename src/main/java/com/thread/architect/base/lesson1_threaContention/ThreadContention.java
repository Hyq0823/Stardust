package com.thread.architect.base.lesson1_threaContention;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * 线程竞争
 * 1)在run方法上不加上synchronized，则可能出现不是我们想要的结果
 */
public class ThreadContention  extends  Thread{
    private int count = 5;
    private CountDownLatch countDownLatch = new CountDownLatch(1);


    /**
     * 1）加上synchronized锁住，多个线程在执行时，就会有竞争
     */
    @Override
    public  void run() {
        try {
            //countDownLatch.wait(); //粗心哦！！！！！，注意是await，而wait是Object类中的方法，否则会报错
            //java.lang.IllegalMonitorStateException

            //采用countDownLatch模拟线程并发
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        count --;
        System.out.println(this.currentThread().getName() + " count: " +  count);
    }

    public static void main(String[] args) throws InterruptedException {
        ThreadContention threadContention = new ThreadContention();

        Thread t1 = new Thread(threadContention,"t1");
        Thread t2 = new Thread(threadContention,"t2");
        Thread t3 = new Thread(threadContention,"t3");
        Thread t4 = new Thread(threadContention,"t4");
        Thread t5 = new Thread(threadContention,"t5");

        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();
        TimeUnit.SECONDS.sleep(5);


        threadContention.countDownLatch.countDown();
    }
}
