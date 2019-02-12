package com.json;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

/**
 * json工具类
 * @Author: huangyunquan
 * @Description:Created on 2017/12/3 11:07.
 */
public class JsonUtils {
    private static Logger log = LoggerFactory.getLogger(JsonUtils.class);

    private final static ObjectMapper objectMapper = new ObjectMapper();
    static {
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM:dd HH:mm:ss"));
    }
	/**
	 *json串转换为javabean 
	 * @param json
	 * @param clazz
	 * @return
	 */
	public static  <T> T json2Obj(String json,Class<T> clazz){
			try {
                return objectMapper.readValue(json, clazz);
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
	}
	
	/**
     * 对象转换为json串
     * 优雅的输出json,自动换行，缩进//.withDefaultPrettyPrinter();
     * @param obj
     * @return
     */
    public static String obj2Json(Object obj){
        ObjectMapper objectMapper = new ObjectMapper();
            ObjectWriter ow = objectMapper.writer();
            try {
                return ow.writeValueAsString(obj);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
                return null;
            }
    }


    public static Map<String, Object> process(String jsonStr) {
        try{
            JSONObject jsonObj = JSONObject.parseObject(jsonStr);
            Map<String,Object> result = new HashMap<>();
            processJsonObj(result,jsonObj,new ArrayList<>());
            return result;
        }catch (Exception e){
            log.info("===========================\r\n");
            log.info("\r\n" + jsonStr + "\r\n");
            log.info("==============================\r\n");
            e.printStackTrace();
            log.error(e.getMessage(),e);
            return null;
        }
    }

    public static void processJsonObj(Map<String,Object> result,JSONObject obj,List<String> names){
        Set<Map.Entry<String, Object>> node = obj.entrySet();
        Iterator<Map.Entry<String,Object>> iterator = node.iterator();
        iterator.forEachRemaining(item -> itemAppender(result,item,names));
    }

    /**
     * 平铺json
     * @param result 最终结果集
     * @param item json项 k-v
     * @param names 路径
     */
    public static void itemAppender(Map<String, Object> result, Map.Entry<String, Object> item, List<String> names) {
        List<String> nameCopy = new ArrayList<>();
        nameCopy.addAll(names);

        nameCopy.add(item.getKey());
        Object value = item.getValue();

        //处理json格式的字符串
        if(value instanceof String && value.toString().startsWith("{")){
            value = JSONObject.parseObject(value.toString());
        }
        if(value instanceof JSONObject){
            JSONObject obj = (JSONObject) value;
            Set<Map.Entry<String, Object>> node = obj.entrySet();
            Iterator<Map.Entry<String,Object>> iterator = node.iterator();
            iterator.forEachRemaining(itemChild -> itemAppender(result,itemChild,nameCopy));
            return;
        }
        if(value instanceof JSONArray){
            JSONArray array = (JSONArray) value;
            if(array.isEmpty()){
                return;
            }

            //Array结构只取第一个元素
            Object o = array.get(0);
            if(o instanceof JSONObject){
                JSONObject obj = (JSONObject) o;
                processJsonObj(result,obj,nameCopy);
            }
            return;
        }
        String name = nameCopy.stream().collect(Collectors.joining("#"));
        result.put(name, value);
    }

    public static void main(String[] args) {

	}
}
