package com.thread.join;

public class Worker3 implements Runnable {
    @Override
    public void run() {
        System.out.println("线程3启动");
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
        System.out.println("线程3完成\r\n");
        System.out.println();
    }
}
