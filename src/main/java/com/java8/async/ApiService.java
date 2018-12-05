package com.java8.async;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

/**
 * @Author: huangyunquan
 * @Description:Created on 2018/12/5 21:07.
 */
@Service
public class ApiService {

    @Autowired
    private RestTemplate restTemplate;

    @Bean(name = "myExecutor")
    public Executor buildExecutor(){
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(3);
        executor.setMaxPoolSize(3);
        executor.setQueueCapacity(100);
        executor.setThreadNamePrefix("hyq线程前缀-");
        executor.initialize();
        return executor;
    }

    @Async("myExecutor")
    public CompletableFuture<String> testBaidu(){

        System.out.println("baidu : "+Thread.currentThread().getName());
        ResponseEntity<String> result = restTemplate.getForEntity("https://www.baidu.com/", String.class);
        String body = result.getBody();
        return CompletableFuture.completedFuture(body);
    }

    @Async("myExecutor")
    public CompletableFuture<String> testSina(){
        System.out.println("sina: "+Thread.currentThread().getName());
        ResponseEntity<String> result =  restTemplate.getForEntity("https://www.sina.com.cn/", String.class);
        String body = result.getBody();
        return CompletableFuture.completedFuture(body);
    }

}
