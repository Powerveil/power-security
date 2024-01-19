package com.power.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Powerveil
 * @Date 2024/1/19 22:59
 */
// 可以标注的范围：方法上和字段上
@Target({ElementType.METHOD, ElementType.FIELD})
// 该注解的Retention是RUNTIME，表示该注解可以被反射获取到
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = MyConstraintValidator.class)
public @interface MyConstraint {
    // 验证失败后，返回的提示信息
    String message();
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
