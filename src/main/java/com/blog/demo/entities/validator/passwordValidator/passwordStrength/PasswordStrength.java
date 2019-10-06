package com.blog.demo.entities.validator.passwordValidator.passwordStrength;


import org.passay.*;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import java.util.Arrays;

import com.google.common.base.Joiner;

/**
 * Created by damiass on Oct, 2019
 */
public class PasswordStrength implements ConstraintValidator<IPasswordStrength, String> {

    @Override
    public void initialize(final IPasswordStrength arg0) {

    }

    /*
    * Passay library for password validation
    * PasswordData - container for information that is necessary for validation
    * (password, username, origin list of password references)
    *  Two objects are required -> PasswordValidator & PasswordData
    * RuleResult has method validate() for validating our password, it is returning RuleResult Object
    * reference to: https://www.baeldung.com/java-passay
    * */

    @Override
    public boolean isValid(String password, ConstraintValidatorContext context) {
        PasswordValidator validator = new PasswordValidator(Arrays.asList(
                new LengthRule(8, 30),
                new UppercaseCharacterRule(1),
                new DigitCharacterRule(1),
                new SpecialCharacterRule(1),
                new NumericalSequenceRule(3,false),
                new AlphabeticalSequenceRule(3,false),
                new QwertySequenceRule(3,false),
                new WhitespaceRule()));

        RuleResult result = validator.validate(new PasswordData(password));
        if (result.isValid()) {
            return true;
        }
        context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate(Joiner.on(",").join(validator.getMessages(result))).addConstraintViolation();
        return false;
    }


}












