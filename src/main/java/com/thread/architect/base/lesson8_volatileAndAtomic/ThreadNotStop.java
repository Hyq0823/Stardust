package com.thread.architect.base.lesson8_volatileAndAtomic;

import java.util.concurrent.TimeUnit;

/**
 * 实验： 采用变量控制，线程并未停止
 */
public class ThreadNotStop {

    /**
     * volatile
     */
    private  Boolean isRuning;

    public Boolean getRuning() {
        return isRuning;
    }

    public void setRuning(Boolean runing) {
        isRuning = runing;
    }

    public ThreadNotStop(Boolean isRuning){
        this.isRuning = isRuning;
    }

    public void method(){
        System.out.println("进入method...");

        while (isRuning){

        }
        System.out.println("线程停止.......");
    }


    public static void main(String[] args) {
        ThreadNotStop threadNotStop = new ThreadNotStop(true);
        new Thread(()->threadNotStop.method(),"t1").start();

        //不加休眠，可能会得到结果
        //进入method...
        //线程停止.......
        //其实是因为，线程还未开始启动，已经被设置成false了

//        try {
//            TimeUnit.SECONDS.sleep(4);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

        //设置runing 为false
        try {
            TimeUnit.SECONDS.sleep(5);
            threadNotStop.setRuning(false);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }




    }
}
