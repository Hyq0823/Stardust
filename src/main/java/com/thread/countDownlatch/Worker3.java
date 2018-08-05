package com.thread.countDownlatch;

import java.util.concurrent.CountDownLatch;

public class Worker3 implements Runnable {
    private CountDownLatch countDownLatch;
    private Bo result;

    public Worker3(Bo result, CountDownLatch countDownLatch){
        this.countDownLatch = countDownLatch;
        this.result = result;
    }



    @Override
    public void run() {
        System.out.println("线程3启动");
        try {
            Thread.sleep(3_000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }




        try {
            Thread.sleep(6_000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("线程3完成\r\n");
        System.out.println();
    }
}
