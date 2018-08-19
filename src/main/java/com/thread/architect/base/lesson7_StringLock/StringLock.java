package com.thread.architect.base.lesson7_StringLock;

/**
 * 字符串，存在常量池，使用 其作为锁，可能会出现问题
 *
 * 从结果可以就看到，线程t2并没有进来，也就说明两个线程持有的是同一个锁
 */
public class StringLock {



    public void method(String lock){
        synchronized (lock){
            while (true){
                System.out.println(Thread.currentThread().getName());
            }
        }
    }


    public static void main(String[] args) {
        StringLock stringLock = new StringLock();

        new Thread(()->stringLock.method("abc"),"t1").start();
        new Thread(()->stringLock.method("abc"),"t2").start();

    }

}
