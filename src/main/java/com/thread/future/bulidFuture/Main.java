package com.thread.future.bulidFuture;

/**
 * @Author: huangyunquan
 * @Description:Created on 2018/9/10 16:12.
 */
public class Main {
    public static void main(String[] args) {
        FutureClient fc = new FutureClient();
        Data data = fc.request("请求参数");
        System.out.println("请求发送成功.....");

        //尚未用到需要计算的返回值时，可以先执行其他逻辑
        System.out.println("做其他的事情......");
        System.out.println("调用其他外界api.......");

        String result = data.getRequest();
        System.out.println(result);
    }
}
