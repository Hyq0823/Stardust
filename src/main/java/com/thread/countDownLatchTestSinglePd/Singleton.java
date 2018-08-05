package com.thread.countDownLatchTestSinglePd;

import java.util.concurrent.CountDownLatch;

public class Singleton {
    private static Singleton instance;

    private Singleton() {
    }

    public static Singleton getInstance() {
        if (instance == null) {
            synchronized (Singleton.class) {
                if (instance == null) {
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }


    public static void main(String[] args) {
        CountDownLatch countDownLatch = new CountDownLatch(1);
        for (int i = 0; i < 10000; i++) {
            new Thread(() -> {
                try {
                    //所有线程都等待锁
                    countDownLatch.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Singleton.getInstance().hashCode());
            }).start();

            //释放锁，模拟线程并发
            countDownLatch.countDown();
        }

    }
}
