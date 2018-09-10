package com.thread.future.bulidFuture;

/**
 * @Author: huangyunquan
 * @Description:Created on 2018/9/10 16:12.
 */
public class FutureClient {

    public Data request(final String queryStr){
        FutureData futureData = new FutureData();

        //创建线程来执行真实结果的查询
        new Thread(()->{
            RealData realData = new RealData(queryStr);
            futureData.setRealData(realData);
        }).start();

        //请求直接返回结果,使其能够执行其他逻辑操作.
        return futureData;
    }

}
