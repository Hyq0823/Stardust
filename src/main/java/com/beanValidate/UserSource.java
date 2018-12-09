package com.beanValidate;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * @Author: huangyunquan
 * @Description:Created on 2018/12/9 20:54.
 * 自定义用户类型约束
 */
@Retention(RetentionPolicy.RUNTIME)
@Target( { ElementType.ANNOTATION_TYPE, ElementType.METHOD, ElementType.FIELD })
@Constraint(validatedBy = {UserTypeValidator.class})
@Documented
public @interface UserSource {
    String message() default "用户来源不正确,请检查!";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
