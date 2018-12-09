package com.beanValidate;

import javax.validation.constraints.*;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * @Author: huangyunquan
 * @Description:Created on 2018/12/9 15:50.
 */
public class User {
    @NotNull(message = "姓名不能为空!")
    private String name;

    @NotEmpty(message = "手机号不能为空及空字符")
    @Size(min = 11, max = 11, message = "手机号为11位")
    private String phone;

//    @NotBlank(message = "邮箱不能是空")
    //javax.validation.UnexpectedTypeException: HV000030: No validator could be found for constraint 'javax.validation.constraints.NotBlank' validating type 'java.util.List<java.lang.String>'. Check configuration for 'emails'

    private List<@NotBlank(message = "存在的邮箱元素不能为空") String> emails;

    private Map<@Email String, @NotNull User> customers;

    private Integer age;

    @UserSource(message = "错误的用户来源!")
    private String source;


    public Optional<@Min(18) Integer> getAge() {
        return Optional.ofNullable(age);
    }


    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Map<String, User> getCustomers() {
        return customers;
    }

    public void setCustomers(Map<String, User> customers) {
        this.customers = customers;
    }

    public List<String> getEmails() {
        return emails;
    }

    public void setEmails(List<String> emails) {
        this.emails = emails;
    }

    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}
