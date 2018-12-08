package com.cors;

import com.alibaba.fastjson.JSONObject;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: huangyunquan
 * @Description:Created on 2018/12/8 11:26.
 *
 * 测试跨域
 */
@RestController
@RequestMapping("/cors")

//@CrossOrigin(maxAge = 1,origins = "*")
public class CorsTestController {

    /**
     * 返回 使用回调函数包裹的json,写死
     * @return
     */
    @RequestMapping("/infoWithCallBack")
    public String info(){
        JSONObject map = new JSONObject();
        map.put("msg","请求成功!");
        map.put("code","0000");
        map.put("data","获取到后端数据!");

        String str = map.toString();
        return "myCallback(" + str + ")";
    }


    /**
     * 直接返回json
     * @return
     */
    @RequestMapping("/info2")
    public String info2(){
        JSONObject map = new JSONObject();
        map.put("msg","请求成功!");
        map.put("code","0000");
        map.put("data","获取到后端数据!");
        return map.toString();
    }



    /**
     * 3.兼容jsonp和普通json请求
     * @return
     */
    @RequestMapping("/info3")
    public String info3(@RequestParam(name="callback",required = false) String callBack){
        JSONObject map = new JSONObject();
        map.put("msg","请求成功!");
        map.put("code","0000");
        map.put("data","获取到后端数据!");
        String result = map.toString();
        if(!StringUtils.isEmpty(callBack)){
            //如果是json请求，则包裹上回调函数
            return callBack + "(" + result + ")";
        }
        return result;
    }
}
