package com.blog.demo.entities.validator.emailValidator;

import com.blog.demo.entities.User;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by damiass on Oct, 2019
 */
public class EmailPatternValidator implements ConstraintValidator<IEmailPattern, User> {


    String regex = "^([_a-zA-Z0-9-]+(\\.[_a-zA-Z0-9-]+)*@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*(\\.[a-zA-Z]{1,6}))?$";
    Pattern pattern = Pattern.compile(regex);


    @Override
    public boolean isValid(User user, ConstraintValidatorContext constraintValidatorContext) {
        Matcher matcher = pattern.matcher(user.getEmail());
        return matcher.matches();
    }



    @Override
    public void initialize(IEmailPattern constraintAnnotation) {

    }
}
