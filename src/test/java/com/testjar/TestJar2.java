package com.testjar;

/**
 * @Author: huangyunquan
 * @Description:Created on 2018/12/6 13:39.
 */
public class TestJar2 {
    public void test2(){
        System.out.println("testJar2输入内容");
    }

    public static void main(String[] args) {
        TestJar1 testJar1 = new TestJar1();
        testJar1.test1();

        TestJar2 testJar2 = new TestJar2();
        testJar2.test2();
    }
}
