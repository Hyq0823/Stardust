package com.hyq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: huangyunquan
 * @Description:Created on 2019/1/16 9:59.
 */
@RestController
@SpringBootApplication
//@EnableAsync
public class Application {

    @RequestMapping("/info")
    String home() {
        return "Hello World!";
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(Application.class, args);
    }
}
