package com.thread.architect.threadLocal;

/**
 * @Author: huangyunquan
 * @Description:Created on 2018/8/23 15:37.
 * threadLocal会为每一个线程创建本地副本
 */
public class TestThreadLocal {
    private ThreadLocal<String> threadLocal = new ThreadLocal<>();

    public void setValue(String value){
        threadLocal.set(value);
    }
    public String get(){
        String threadName = Thread.currentThread().getName();
        String s = threadLocal.get();
        System.out.println(threadName + "get value......  "  + s);
        return s;
    }

    public static void main(String[] args) {
        TestThreadLocal testThreadLocal = new TestThreadLocal();
        new Thread(()->{
            testThreadLocal.setValue("t1 value");
        },"t1").start();
        new Thread(()->{
            testThreadLocal.get();
        },"t2").start();
        new Thread(()->{
            testThreadLocal.get();
        },"t3").start();
    }
}
