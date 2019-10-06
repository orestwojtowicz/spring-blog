package com.blog.demo.entities.validator.emailValidator;

import com.blog.demo.entities.validator.passwordValidator.PasswordMatchValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * Created by damiass on Oct, 2019
 */
@Documented
@Constraint(validatedBy = EmailPatternValidator.class)
@Target({ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface IEmailPattern {

    String message() default "Invalid Email Address Format";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

}
