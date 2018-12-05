package com.java8;

import com.java8.async.ApiService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * @Author: huangyunquan
 * @Description:Created on 2018/12/5 21:06.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@WebAppConfiguration
public class TestFuture {

    @Autowired
    private ApiService apiService;


    @Test
    public void test() throws InterruptedException, ExecutionException, TimeoutException {
        CompletableFuture<String> futureBaidu = apiService.testBaidu();
        CompletableFuture<String> futureSina = apiService.testSina();

        String baidu = futureBaidu.get(10, TimeUnit.SECONDS);
        String sina = futureSina.get(10, TimeUnit.SECONDS);
        System.out.println(baidu);
        System.out.println(sina);


    }


    public static void main(String[] args) {

    }
}
