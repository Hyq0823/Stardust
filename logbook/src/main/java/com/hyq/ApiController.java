package com.hyq;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @Author: huangyunquan
 * @Description:Created on 2019/1/27 15:45.
 */
@RestController
@RequestMapping("/api")
public class ApiController {

    /**
     *
     * @return
     */
    @RequestMapping("/test")
    public  Map<String,String> api(@RequestBody Map<String,String> map){
        map.put("result","ok");
        return map;
    }
}
