package com.thread.future.bulidFuture;

import java.util.concurrent.TimeUnit;

/**
 * @Author: huangyunquan
 * @Description:Created on 2018/9/10 16:09.
 */
public class RealData implements Data {
    private String result;

    public RealData(String queryStr){
        System.out.println("耗时操作,根据参数查询: 参数：" + queryStr);
        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        result = "data result !";
    }


    @Override
    public String getRequest() {
        return result;
    }
}
