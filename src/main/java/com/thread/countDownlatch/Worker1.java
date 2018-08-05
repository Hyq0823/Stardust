package com.thread.countDownlatch;

import java.util.concurrent.CountDownLatch;

public class Worker1 implements Runnable {
    private CountDownLatch countDownLatch;
    private Bo result;

    public Worker1(Bo result, CountDownLatch countDownLatch){
        this.result = result;
        this.countDownLatch = countDownLatch;
    }


    @Override
        public void run() {
        String result = this.result.getResult();
        this.result.setResult(result +" 线程1完成任务");

        this.result.getMap().put("线程1","线程1value");
        System.out.println("线程1完成任务");
        System.out.println();
        countDownLatch.countDown();

        System.out.println("线程1启动");
        try {
            Thread.sleep(3_000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }




        try {
            Thread.sleep(3_000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("线程1完成\r\n");
    }
}
