package com.blog.demo.entities.validator.passwordValidator.passwordMatch;

import com.blog.demo.entities.User;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Created by damiass on Oct, 2019
 */
public class PasswordMatchValidator implements ConstraintValidator<IPasswordMatch, User> {

    @Override
    public boolean isValid(User user, ConstraintValidatorContext context) {
        return user.getPassword().equals(user.getConfirmPassword());
    }

    @Override
    public void initialize(IPasswordMatch constraintAnnotation) {

    }

}
