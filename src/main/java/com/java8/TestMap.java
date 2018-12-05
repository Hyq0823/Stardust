package com.java8;

import com.java8.intf.Counter;
import com.other.intf.People;
import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.Optional;

/**
 * @Author: huangyunquan
 * @Description:Created on 2018/12/3 15:33.
 */
public class TestMap {
    /**
     * 实现接口
     */
    static Counter counter = ()->1;
    public static void main(String[] args) {
        Integer count = counter.count();
        System.out.println(count);
    }


    @Test
    public void testOptional(){
        Map map = null;
        Object kqy = Optional.ofNullable(map)
                .map(r -> r.get("key"))
                .map(str->"prefix" + str)
                .orElse("");
        System.out.println(kqy);
    }

    @Test
    public Optional<People> buildPeople(){
        //...各种逻辑
        People people = null;

        Optional.ofNullable(people)
                //...可以有无穷个中间操作
                .filter(p->p.getName().startsWith("侠梦"))
                .map(p->p.getMoney())
                //终端操作,消费掉
                .ifPresent(System.out::print);


        return null;
    }

//    @Test(expected=IllegalArgumentException.class)
    public void test(){
        School school = null;
        if(school != null){
            Clazz clazz = school.getClazz();
            if(clazz != null){
                Student student = clazz.getStudent();
                if(student != null){
                    String name = student.getName();
                    if(name == null || "".equals(name)){
                        name = "无名氏";
                    }

                }
            }
        }
        String name = Optional.ofNullable(school)
                .map(School::getClazz)
                .map(Clazz::getStudent)
                .map(Student::getName)
                .orElse("无名氏");
    }



}
