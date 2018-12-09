package com.beanValidate;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.*;

/**
 * @Author: huangyunquan
 * @Description:Created on 2018/12/9 15:52.
 */
public class UserBeanValidateTest {
    private static  Validator validator;

    @BeforeClass
    public static void before(){
         validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    /**
     * 校验规则：object != null;
     */
    @Test
    public void testNotNull(){
        User user = new User();
        user.setName("侠梦"); //size=0
//        user.setName(""); //size =0
//        user.setName(null); //size = 1
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        Assert.assertEquals(violations.size(),0);
    }

    @Test
    public void testNotEmpty(){
        User user = new User();
        user.setName("");
        user.setPhone("11111");
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        violations.stream().forEach(v->System.out.print(v.getMessage()));
    }

    @Test
    public void testContainerNotBlank(){
        User user = new User();
        user.setName("");

        //错误会输出三次
        List<String> emails = new ArrayList<>();
        emails.add("");
        emails.add("    ");
        emails.add(null);

        user.setEmails(emails);
        user.setPhone("11111111111");
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        violations.stream().forEach(v->System.out.println(v.getMessage()));
    }

    @Test
    public void testMapNotNull(){
        User user = new User();
        user.setName("侠梦");
        List<String> emails = new ArrayList<>();
        emails.add("11");
        user.setEmails(emails);
        user.setPhone("11111111111");

        Map map = new HashMap<>();
//        map.put("1",user);
        map.put("823547749@qq.com",user);
        user.setCustomers(map);
        user.setSource("aaa");

        Set<ConstraintViolation<User>> violations = validator.validate(user);
        violations.stream().forEach(v->System.out.println(v.getMessage()));
    }


    @Test
    public void testOptionalInteger(){
        User user = new User();
        user.setName("");
        List<String> emails = new ArrayList<>();
        emails.add("11");
        user.setEmails(emails);
        user.setPhone("11111111111");
        Map map = new HashMap<>();
        map.put("823547749@qq.com",user);
        user.setCustomers(map);
        user.setAge(17);


        Set<ConstraintViolation<User>> violations = validator.validate(user);
        violations.stream().forEach(v->System.out.println(v.getMessage()));
    }
}
