package com.blog.demo.entities.validator.whiteSpaceValidator;



import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

import static java.lang.annotation.ElementType.*;

/**
 * Created by damiass on Oct, 2019
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ TYPE, FIELD, ANNOTATION_TYPE })
@Constraint(validatedBy = WhiteSpaceValidator.class)
public @interface IWhiteSpace {

    String message() default "Please remove white space in your user name and user name must start with character";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};


}
