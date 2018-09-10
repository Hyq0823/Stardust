package com.thread.future.bulidFuture;

/**
 * @Author: huangyunquan
 * @Description:Created on 2018/9/10 16:13.
 */
public class FutureData implements Data {
    private RealData realData;
    private boolean isRead = false;


    public synchronized void setRealData(RealData realData){
        if(isRead){
            return;
        }

        this.realData = realData;
        isRead = true;
        notify();
    }


    @Override
    public synchronized String getRequest() {
        while (!isRead){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return this.realData.getRequest();
    }
}
