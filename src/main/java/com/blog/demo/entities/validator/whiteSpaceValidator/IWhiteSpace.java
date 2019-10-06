package com.blog.demo.entities.validator.whiteSpaceValidator;



import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * Created by damiass on Oct, 2019
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Constraint(validatedBy = WhiteSpaceValidator.class)
public @interface IWhiteSpace {

    String message() default "Please remove white space in your user name";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};


}
