package com.spring.async;

import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * @Author: huangyunquan
 * @Description:Created on 2018/11/9 17:01.
 */
@RequestMapping("async")
@RestController
public class TestAsyncController {
    @RequestMapping("/test")
    @Async
    public String test() throws InterruptedException {
        System.out.println("in..............");
        String test2Sleep = test2Sleep();
        return test2Sleep;
    }


    @Async
    public String test2Sleep() throws InterruptedException {
        TimeUnit.SECONDS.sleep(10);
        return "innner method";
    }
}
