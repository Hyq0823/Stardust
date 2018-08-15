package com.hl.hlf;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import java.util.LinkedHashMap;

/**
 * Unit test for simple App.
 */
public class AppTest 
{

    public static void main(String[] args) {
//        test1();
        test2();
    }

    private static void test2() {
        LinkedHashMap map = new LinkedHashMap();
        map.put("a","1");
        map.put("c","3");
        map.put("b","2");


        map.forEach((key,value)->{
            System.out.println(key + "   " + value);
        });
    }

    private static void test1() {
        Result result = JUnitCore.runClasses(Example.class);
        for(Failure failure : result.getFailures()){
            System.out.println(failure.toString());
        }
    }

    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue()
    {
        Integer a= 1;
        Integer b = 1;
        assertTrue(a==b );


    }
}
