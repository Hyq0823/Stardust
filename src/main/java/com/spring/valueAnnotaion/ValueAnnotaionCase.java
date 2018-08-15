package com.spring.valueAnnotaion;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * Could not resolve placeholder 'name' in value "${name}"
 * @PostConstruct: 让spring实例化bean后，立马调用被注解的方法，此注解可以用于多个方法
 *
 * @Value注解深入学习
 * 1）可以定义为常量，如    //@Value("hyq") //定义常量
 * 2）从vm options中赋值， 指定 -Dname=hyq2222,（此项比4 优先级高）
 * 3)运行jar包时，指定,如： java -jar app.jar --name="Spring" (优先级比4高)
 * 4)新建application.properties，指定name
 *
 *
 *
 * 通过在类上指定@PropertySource 读取一个文件
 */
@Component
public class ValueAnnotaionCase {

    @Value("${name}") //定义常量
    private String name;

    /**
     * 值不存在时，使用默认值
     */
    @Value("${value.totalyNotExists : you can do it , hyq,just believe youself  and fight }")
    private String defaultValue;


    /**
     * 不能以逗号结尾，否则会有4个元素
     * listOfValues=A,B,C,
     */
    @Value("${listOfValues}")
    private String[] valuesArray;


    /**
     * 转为List
     */
    @Value("#{'${listOfValues}'.split(',')}")
    private List<String> valueList;

    /**
     * 系统变量
     */
    @Value("#{systemProperties[user.dir]}")
    private String userDir;


    @PostConstruct
    public void init(){
        System.out.println("初始化name: " + name);
        System.out.println("defaultValue: " + defaultValue);
        for(String a : valuesArray){
            System.out.println(a);
        }
        valueList.forEach(System.out::println);
        System.out.println(userDir);

    }


}
