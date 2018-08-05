package com.thread.countDownlatch;

import java.util.concurrent.CountDownLatch;

public class Worker2 implements Runnable {
    private CountDownLatch countDownLatch;
    private Bo result;

    public Worker2(Bo result, CountDownLatch countDownLatch){
        this.result = result;
        this.countDownLatch = countDownLatch;
    }



    @Override
    public void run() {
        System.out.println("线程2启动");
        try {
            Thread.sleep(3_000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        this.result.getMap().put("线程2","线程2value");

        String result = this.result.getResult();
        this.result.setResult(result +" 线程2完成任务");
        System.out.println("线程2完成任务");
        System.out.println();

        //使用灵活，主线程不必等到线程完成
        countDownLatch.countDown();

        try {
            Thread.sleep(3_000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("线程2完成\r\n");
    }
}
