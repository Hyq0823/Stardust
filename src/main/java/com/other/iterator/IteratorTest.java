package com.other.iterator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @Author: huangyunquan
 * @Description:Created on 2018/11/21 14:46.
 */
public class IteratorTest {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("a");
        list.add("b");
        list.add("c");
        Iterator<String> it = list.iterator();
        while (it.hasNext()){
            String next = it.next();
            System.out.println(next);
        }

        System.out.println("sssssb");

    }
}
