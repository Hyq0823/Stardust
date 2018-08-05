package com.thread.join;

public class Worker2 implements Runnable {
    @Override
    public void run() {
        System.out.println("线程2启动");
        try {
            Thread.sleep(3_000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("线程2完成\r\n");
    }
}
