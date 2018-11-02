package com.hl.hlf;

import com.Start;
import com.spring.valueAnnotaion.SpringContextHolder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes= Start.class)
public class Example {


    @Test
    public void test(){
        String test = SpringContextHolder.getContext().getEnvironment().getProperty("tahceCname");
        System.out.println(test);
    }

}
