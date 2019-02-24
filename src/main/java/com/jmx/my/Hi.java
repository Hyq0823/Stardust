package com.jmx.my;

/**
 * @Author: huangyunquan
 * @Description:Created on 2019/2/20 18:39.
 */
public class Hi implements HiMBean {

    private String name="hyq";
    @Override
    public void sayHi() {
        System.out.println("hi i'm something");
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name=name;
    }
}
