package com.java8.async;


import org.junit.jupiter.api.Test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * @Author: huangyunquan
 * @Description:Created on 2018/5/2 11:16.
 */
public class CompletableFutureTest {


    public static void main(String[] args) {

    }

    /**
     * 调用此方法，是主线程调用，在方法内部，子线程执行大量耗时的计算
     * 主线程可能先与子线程结束，由于主线程要用到子线程的处理结果，但是此时子线程又没有执行完
     * 只好通过join()主线程等待子线程执行完毕再消亡。
     *
     * 子线程再main中创建,在main中调用了 子线程.join方法，那么意味着 main线程必须等待子线程执行完毕后再消亡。
     *
     *
     * ThenAccept是对结果进行消耗，入参是Consumer，所以无返回值。
     * @throws Exception
     */
    @Test
    public  void testAcceptAndJoin() throws Exception{

        //这里是主线程main
        System.out.println(Thread.currentThread().getName());

        //执行异步计算,然后消费
        CompletableFuture.supplyAsync(() ->{
            try {
                //这里是ForkJoinPool.commonPool-worker-1线程
                System.out.println(Thread.currentThread().getName());
                Thread.sleep(4 * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "hello";
        }).thenAccept(s -> {
            System.out.println(s + "world");
            System.out.println(Thread.currentThread().getName());

        }).join();
        System.out.println("main end----");

    }

    /**
     * Thenapply 的入参是上一个阶段的计算结果。
     * 输出参数是： 经过Function函数转换后的结果。
     */
    @Test
    public void testApply() throws ExecutionException, InterruptedException {

        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(3 * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "supply ";
        }).thenApply(s -> {
            return s + " -abc";
        });

//        String s = future.
        System.out.println(future.isDone());
        System.out.println(future);
    }

    /**
     * 对上一步执行的结果不关心，
     * 只关心是否执行完毕。
     * 执行下一个操作。
     *
     */
    @Test
    public void testThenRun(){
        CompletableFuture.supplyAsync(()->{
            System.out.println(Thread.currentThread().getName());
            return "testThenRun";
        }).thenRun(()->{
            System.out.println(Thread.currentThread().getName());
            System.out.println("aaa");
        }).join();
    }


    /**
     * 测试整合两个异步操作
     * get（）方法会通过while（true） 来判断是否已经计算出结果了
     * 而get(1,TimeUnit) 则在超过等待时间后抛出TimeOut异常来提示用户
     */
    @Test
    public void testCombine() throws ExecutionException, InterruptedException, TimeoutException {
        String join = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(10 * 1000); // 休息3秒钟
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return " 第一个异步操作返回的结果 ";
        }).thenCombine(CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(5 * 1000);
            } catch (InterruptedException e) {
            }
            return " \r\n第二个异步操作返回的结果 ";
        }), (s1, s2) -> {
            return (s1 + s2 + "---oprate end...");
        }).get(3, TimeUnit.SECONDS);
        System.out.println(join);
    }


    /**
     * 测试整合两个操作结果，对结果进行直接消耗，不返回值
     * 使用Api: thenAcceptBoth方法
     */
    @Test
    public void testAcceptBoth() throws ExecutionException, InterruptedException, TimeoutException {
        CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(3 * 1000); // 休息3秒钟
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return " 第一个异步操作返回的结果 ";
        }).thenAcceptBoth(CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(5 * 1000);
            } catch (InterruptedException e) {
            }
            return " \r\n第二个异步操作返回的结果 ";
        }),(r,r2)->{
            System.out.println(r + "---" + r2 + "  end ");
        }).join();
    }



    /**
     * 测试整合两个操作结果
     * 不关心操作的返回值，只关心其是否结束
     * 不关心这两个CompletionStage的结果，只关心这两个CompletionStage执行完毕，之后在进行操作（Runnable）
     *
     * 对结果进行直接消耗，不返回值
     * 使用Api: runAfterBoth方法
     */
    @Test
    public void testRunAfterBoth() throws ExecutionException, InterruptedException, TimeoutException {
        CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(3 * 1000); // 休息3秒钟
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return " 第一个异步操作返回的结果1 ";
        }).runAfterBoth(CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(5 * 1000);
            } catch (InterruptedException e) {
            }
            return " \r\n第二个异步操作返回的结果2 ";
        }),()->{
            System.out.println(  "---" + " runable  end ");
        }).join();
    }


    /**
     * 测试ApplyToEither,
     *
     * 两个操作谁计算得快就用谁
     *
     * 在日常的开发中，总会遇到的开发场景是：
     *
     * 两种渠道获取结果： 谁计算的快就用谁，通过applyToEither寻找
     * 最快的一个操作。
     *
     * 同理： acceptEither也是一样的
     */
    @Test
    public void testApplyToEither(){
        String result = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(3 * 1000); // 休息3秒钟
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return " 第一个操作睡眠3秒 ";
        }).applyToEither(CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(5 * 1000);
            } catch (InterruptedException e) { }
            return " 第二个操作睡眠5秒  ";
        }), (r) -> {
            return r + "---" + " applyToEither end ";
        }).join();
        System.out.println(result);
    }


    /**
     * 测试： 两个CompletationStage操作，任何一个完了都会执行下一个
     */
    @Test
    public void testRunAfterEither(){
        CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(3 * 1000); // 休息3秒钟
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return " 第一个操作睡眠3秒 ";
        }).runAfterEither(CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(5 * 1000);
            } catch (InterruptedException e) { }
            return " 第二个操作睡眠5秒  ";
        }), () -> {
            System.out.println( "---" + " applyToEither end ");
        }).join();
    }

}


