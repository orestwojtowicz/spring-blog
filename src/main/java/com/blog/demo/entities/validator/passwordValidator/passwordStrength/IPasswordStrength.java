package com.blog.demo.entities.validator.passwordValidator.passwordStrength;

import com.blog.demo.entities.validator.passwordValidator.passwordMatch.PasswordMatchValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by damiass on Oct, 2019
 */
@Documented
@Constraint(validatedBy = PasswordStrength.class)
@Target({ TYPE, FIELD, ANNOTATION_TYPE })
@Retention(RUNTIME)
public @interface IPasswordStrength {

    String message() default "Password is not secured";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
