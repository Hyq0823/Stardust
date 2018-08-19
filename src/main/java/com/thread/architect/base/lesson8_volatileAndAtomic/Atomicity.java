package com.thread.architect.base.lesson8_volatileAndAtomic;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 原子性
 * 观察最后count的值，是否是1w
 */
public class Atomicity {
    private CountDownLatch countDownLatch = new CountDownLatch(1);
    private volatile int count;
    private AtomicInteger atomicInteger = new AtomicInteger(0);

    /**
     * ++i和i++操作并不是线程安全的
     * 使用volatile 时，不加synchronized，证明，volatile不具备同步功能
     *
     * 但是如果使用场景只用在控制一个计数的整型变量时(通常这也是非常常见的一个使用场景)，
     * 另一种实现方式AtomicInteger类简洁易用的特性一定会让你对它爱不释手
     *
     * AtomicInteger则通过一种线程安全的加减操作接口，底层是有volatile修饰的变量作为共享变量
     * AtomicInteger类可以用原子方式更新的 int 值，主要用于在高并发环境下的高效程序处理，使用非阻塞算法来实现并发控制
     *
     * 如果程序中，只需要控制计数，则使用AtomicInteger效果更好
     */
    public   void method(){
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for(int i =0;i<1000;i++){
            count ++;
//            atomicInteger.incrementAndGet();

            //多个addAndGet在一个方法内是非原子性的，需要加synchronized进行修饰，保证4个addAndGet整体原子性
            atomicInteger.addAndGet(1);
            atomicInteger.addAndGet(2);
            atomicInteger.addAndGet(4);
            atomicInteger.addAndGet(3);
            int temp = atomicInteger.get();

            //如果4个加法，是原子性的，这里的值应都被10整除
            if(temp % 10 != 0) {
                System.out.println(temp);
            }
        }
        //System.out.println("count: " + count);
        System.out.println("atomicInteger: " + atomicInteger);
    }

    public static void main(String[] args) {
        Atomicity atomicity = new Atomicity();
        Thread[] threads = new Thread[10];


        for(int i =0;i<10;i++){
            Thread thread = new Thread(() -> {
                atomicity.method();
            });
            threads[i] = thread;
        }

        for (int i =0;i<10;i++){
            threads[i].start();
        }
       atomicity.countDownLatch.countDown();
    }
}

