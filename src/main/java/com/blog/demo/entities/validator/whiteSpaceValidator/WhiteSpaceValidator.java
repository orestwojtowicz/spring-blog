package com.blog.demo.entities.validator.whiteSpaceValidator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Created by damiass on Oct, 2019
 */
public class WhiteSpaceValidator implements ConstraintValidator<IWhiteSpace, String> {

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {

        return s.isEmpty();
    }

    @Override
    public void initialize(IWhiteSpace constraintAnnotation) {

    }
}
