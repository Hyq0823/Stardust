package com.thread.future;

import java.util.concurrent.*;

/**
 * @Author: huangyunquan
 * @Description:Created on 2018/9/10 15:21.
 */
public class UseFuture {


    static class UseCallable implements Callable<String>{
        private String param;

        public UseCallable(String param) {
            this.param = param;
        }

        @Override
        public String call() throws Exception {
            //TimeUnit.SECONDS.sleep(1);
            return param + " is done !";
        }
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        UseCallable query1 = new UseCallable("query1");
        UseCallable query2 = new UseCallable("query2");

        FutureTask<String> futureTask1 = new FutureTask<>(query1);
        FutureTask<String> futureTask2 = new FutureTask<>(query2);

        ExecutorService executorService = Executors.newFixedThreadPool(2);
       // executorService.execute(futureTask1);
        Future<?> future1 = executorService.submit(futureTask1);
        Future<?> future2 = executorService.submit(futureTask2);




        try {
            //这里可以做额外的数据操作，也就是主程序执行其他业务逻辑
            System.out.println("处理实际的业务逻辑...");
            //Thread.sleep(5000);
            TimeUnit.SECONDS.sleep(30);
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println(future1.isDone());
        System.out.println(future1.get());
        System.out.println(future2.get());


       // TimeUnit.SECONDS.sleep(10);


    }
}
