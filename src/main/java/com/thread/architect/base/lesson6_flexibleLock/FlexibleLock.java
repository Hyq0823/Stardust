package com.thread.architect.base.lesson6_flexibleLock;


import java.util.concurrent.TimeUnit;

public class FlexibleLock {

    private Object lock = new Object();


    public void method(){

        System.out.println("执行一段复杂的业务..不需要加锁");
        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        synchronized(lock){
            System.out.println("需要控制同步的业务逻辑");
        }


        System.out.println("执行完毕....");
    }

    public static void main(String[] args) {
        FlexibleLock flexibleLock = new FlexibleLock();


        new Thread(()-> flexibleLock.method(),"ttt").start();
    }
}
