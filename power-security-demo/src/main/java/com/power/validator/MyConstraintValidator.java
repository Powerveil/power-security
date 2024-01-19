package com.power.validator;

import com.power.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author Powerveil
 * @Date 2024/1/19 23:02
 */
// 这里不用@Component，因为ValidatorFactoryBean会自动注入
public class MyConstraintValidator implements ConstraintValidator<MyConstraint, Object> {
    @Autowired
    private HelloService helloService;

    @Override
    public void initialize(MyConstraint constraintAnnotation) {
        System.out.println("my validator initialize");
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        String greeting = helloService.greeting("tom");
        System.out.println("=====================================");
        System.out.println("MyConstraintValidator:value" + value);
        System.out.println("MyConstraintValidator:greeting" + greeting);
        System.out.println("=====================================");
        return false;
    }
}
