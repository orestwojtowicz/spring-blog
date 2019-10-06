package com.blog.demo.entities.validator.passwordValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * Created by damiass on Oct, 2019
 */
@Documented
@Constraint(validatedBy = PasswordMatchValidator.class)
@Target({ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface IPasswordMatch {

    String message() default "Password & Password Confirmation do not match";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

}
