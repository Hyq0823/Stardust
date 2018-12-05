package com.java8.sorted;

import java.util.stream.Stream;

/**
 * @Author: huangyunquan
 * @Description:Created on 2018/12/5 9:18.
 */
public class SortedDemo {
    public static void main(String[] args) {
        Stream.of("d2", "a2", "b1", "a3", "c")
                .sorted((s1, s2) -> {
                    System.out.printf("sort: %s; %s\n", s1, s2);
                    return s1.compareTo(s2);
                })
                .filter(s -> {
                    System.out.println("filter:  " + s);
                    return s.startsWith("a");
                })
                .map(s -> {
                    System.out.println("map: " + s);
                    return s.toUpperCase();
                })
                .forEach(s -> System.out.println("forEach: " + s));
                //A2
    }
}
