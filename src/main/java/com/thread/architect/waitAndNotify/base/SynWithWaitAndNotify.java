package com.thread.architect.waitAndNotify.base;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 采用wait / notify 实现，一定要配合synchronized使用
 *    //由于 wait方法释放锁，notify方法不释放锁，所以
 *         //如果t1在前，则t2根本不能收到通知
 *         //反之，如果t2先执行，wait后，会释放锁，所以能够执行停止
 *         //但是美中不足的是，t1执行到5后，还是会继续向下执行，在数据量大时等业务场景下，可能造成问题
 *         //比如t1线程是在大数据量中 获取一个结果，只要找到就通知t2，则t1应该不再继续向下找。
 *         //不应该是每次找完数据，才通知t2
 *
 *
 *         wait notify为什么要配合synchronized使用
 *         wait()方法的语义有两个，一是释放当前对象锁，另一个是进入阻塞队列
 *
 *
 *         为什么这三个方法要与synchronized一起使用呢？解释这个问题之前，我们先要了解几个知识点
 * 每一个对象都有一个与之对应的监视器
 * 每一个监视器里面都有一个该对象的锁和一个等待队列和一个同步队列
 * wait()方法的语义有两个，一是释放当前对象锁，另一个是进入阻塞队列，可以看到，这些操作都是与监视器相关的，当然要指定一个监视器才能完成这个操作了
 *
 * notify()方法也是一样的，用来唤醒一个线程，你要去唤醒，首先你得知道他在哪儿，所以必须先找到该对象，也就是获取该对象的锁，当获取到该对象的锁之后，才能去该对象的对应的等待队列去唤醒一个线程。值得注意的是，只有当执行唤醒工作的线程离开同步块，即释放锁之后，被唤醒线程才能去竞争锁。
 *
 * notifyAll()方法和notify()一样，只不过是唤醒等待队列中的所有线程
 *
 * 因wait()而导致阻塞的线程是放在阻塞队列中的，因竞争失败导致的阻塞是放在同步队列中的，notify()/notifyAll()实质上是把阻塞队列中的线程放到同步队列中去
 *
 * 为了便于理解，你可以把线程想象成一个个列车，对象想象成车站，每一个车站每一次能跑一班车，这样理解起来就比较容易了。
 * 值得提的一点是，synchronized是一个非公平的锁，如果竞争激烈的话，可能导致某些线程一直得不到执行。
 *
 *
 */
public class SynWithWaitAndNotify {
    private List list = new ArrayList<>();


    public int size(){
        return list.size();
    }

    public void add(Object obj){
        list.add(obj);
    }


    public static void main(String[] args) {
        SynWithWaitAndNotify synWithWaitAndNotify = new SynWithWaitAndNotify();
        Object lock = new Object();

        new Thread(()->{

            synchronized (lock){
                for(int i = 0;i<=10;i++){
                    synWithWaitAndNotify.add(i);

                    try {
                        TimeUnit.SECONDS.sleep(2);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(i);
                    if(synWithWaitAndNotify.size() == 5){
                        System.out.println("已经发出通知..");
                        lock.notify();
                    }
                }
            }
        },"t1").start();



        new Thread(()->{
            synchronized (lock){
                if (synWithWaitAndNotify.size() != 5){
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            System.out.println(" t2 stop ");
            throw new RuntimeException();
        },"t2").start();


    }

}
