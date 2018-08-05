package com.thread.join;

import java.sql.SQLOutput;

public class Worker1 implements Runnable {
    @Override
    public void run() {
        System.out.println("线程1启动");
        try {
            Thread.sleep(3_000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("线程1完成\r\n");
    }
}
