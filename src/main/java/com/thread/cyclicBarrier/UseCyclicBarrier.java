package com.thread.cyclicBarrier;

import java.util.Random;
import java.util.concurrent.*;

/**
 * @Author: huangyunquan
 * @Description:Created on 2018/9/10 14:16.
 */
public class UseCyclicBarrier {

    static  class Runner implements Runnable{
        private CyclicBarrier cyclicBarrier;
        private String name;

        public Runner(CyclicBarrier cyclicBarrier, String name) {
            this.cyclicBarrier = cyclicBarrier;
            this.name = name;
        }

        @Override
        public void run() {

            try {

                //随机睡眠1 - 5秒
                TimeUnit.SECONDS.sleep(new Random().nextInt(5));
                System.out.println(name + " 准备ok..");
                int numberWaiting = cyclicBarrier.getNumberWaiting();
                System.out.println("当前等待的数量：" + numberWaiting);
                cyclicBarrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
            System.out.println(name + " GO ");
        }
    }

    public static void main(String[] args) {
        CyclicBarrier barrier = new CyclicBarrier(3);

        Runner runner1 = new Runner(barrier,"张三");
        Runner runner2 = new Runner(barrier,"李四");
        Runner runner3 = new Runner(barrier,"王五");
        Runner runner4 = new Runner(barrier,"赵六");

        ExecutorService executorService = Executors.newFixedThreadPool(3);
        executorService.submit(runner1);
        executorService.submit(runner2);
        executorService.submit(runner3);
        executorService.submit(runner4);
    }
}
