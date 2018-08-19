package com.thread.architect.waitAndNotify.base;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 实验：创建两个线程
 * 线程1，向容器中加入10个元素
 *
 * 线程2：一直运行，当容器中有5个元素时停止
 */
public class ListAddNotify {
    private volatile List list = new ArrayList();

    public void add(Object obj){
        list.add(obj);
    }

    public int size(){
        return list.size();
    }


    public static void main(String[] args) {
//         List list = new ArrayList();

        ListAddNotify listAddNotify = new ListAddNotify();

        new Thread(()->{
            for(int i =0;i<=10;i++){
                listAddNotify.add(i);
                System.out.println(Thread.currentThread().getName() + " add element....." + i);
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"t1").start();


        new Thread(()->{
            while (listAddNotify.size() != 5){

                //在不使用volatile的情况下，在这里打印size,则仍然可以实现通知的效果，为啥？
//                System.out.println(listAddNotify.size());
            }

            System.out.println(Thread.currentThread().getName() + "   stop !");
            throw new RuntimeException();
        },"t2").start();


    }
}
