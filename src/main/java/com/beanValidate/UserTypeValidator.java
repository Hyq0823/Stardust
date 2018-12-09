package com.beanValidate;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;
import java.util.List;

/**
 * @Author: huangyunquan
 * @Description:Created on 2018/12/9 20:54.
 */
public class UserTypeValidator implements ConstraintValidator<UserSource, String> {
    private List<String> sources = Arrays.asList(new String[]{"wechat","qq","weibo"});
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
       return sources.contains(value);
    }
}
