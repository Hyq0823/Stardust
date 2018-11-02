package com.other.string;

/**
 * @Author: huangyunquan
 * @Description:Created on 2018/10/30 18:07.
 */
public class StringTest {
    public static void main(String[] args) {
        String url = "http://marathon-lb-external.marathon.mesos:8000/2i2c/newactivateshops";
       // url = url.endsWith("/")? url.substring(0,url.lastIndexOf("/")) : url;
        String[] split = url.split("/");

        System.out.println(split);

    }
}
