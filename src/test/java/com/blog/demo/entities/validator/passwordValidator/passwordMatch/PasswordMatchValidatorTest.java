package com.blog.demo.entities.validator.passwordValidator.passwordMatch;

import com.blog.demo.entities.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by damiass on Oct, 2019
 */
class PasswordMatchValidatorTest {

    private static Validator validator;
    private User user;

    @BeforeEach
    void setUp() {
        ValidatorFactory facotry = Validation.buildDefaultValidatorFactory();
        validator = facotry.getValidator();
        user = new User();
    }


    @Test
    void confirmPasswordValidationTest() {
        user.setPassword("password");
        user.setConfirmPassword("password");
        Set<ConstraintViolation<String>> constraintViolations = validator.validate(user.getPassword());
        assertEquals(constraintViolations.size(), 0);
    }

    @Test
    void confirmPasswordDoesNotMatchTest() {
        user.setPassword("password");
        user.setConfirmPassword("passwordddd");
        Set<ConstraintViolation<User>> constraintViolations = validator.validateProperty(user, "password");
        assertEquals(1, constraintViolations.size());

    }

    // check if strong password is working
    // not strong password used in this case - test fail
    @Test
    void passwordStrengthFailTest() {
        user.setPassword("password");
        user.setConfirmPassword("password");
        Set<ConstraintViolation<User>> constraintViolations = validator.validateProperty(user, "password");
        assertEquals(1, constraintViolations.size());

    }

    // strong password is given
    @Test
    void passwordStrengthPassTest() {
        user.setPassword("Pawelek30!@");
        user.setConfirmPassword("Pawelek30!@");
        Set<ConstraintViolation<User>> constraintViolations = validator.validateProperty(user, "password");
        assertEquals(0, constraintViolations.size());

    }

    // check if messages are displaying correctly
    //                new LengthRule(8, 100), OK
    //                new UppercaseCharacterRule(1), OK
    //                new DigitCharacterRule(1), NO
    //                new SpecialCharacterRule(1), NO
    //                new NumericalSequenceRule(3,false), NO
    //                new AlphabeticalSequenceRule(3,false), OK
    //                new QwertySequenceRule(3,false), OK
    //                new WhitespaceRule())); OK
    @Test
    void passwordStrengthMessageTest() {
        user.setPassword("Password");
        user.setConfirmPassword("Password");
        Set<ConstraintViolation<User>> constraintViolations = validator.validateProperty(user, "password");
        Set<String> messages = new HashSet<>();
        messages.add(constraintViolations.iterator().next().getMessage());
        messages.forEach(System.out::println);
        // Password must contain at least 1 digit characters.,Password must contain at least 1 special characters.
        assertEquals(1, constraintViolations.size());
        assertEquals(messages.iterator().next(), constraintViolations.iterator().next().getMessage());

    }

    @Test
    void passwordStrengthMessageWithoutDigitTest() {
        user.setPassword("Password!@#");
        user.setConfirmPassword("Password!@#");
        Set<ConstraintViolation<User>> constraintViolations = validator.validateProperty(user, "password");
        Set<String> messages = new HashSet<>();
        messages.add(constraintViolations.iterator().next().getMessage());
        messages.forEach(System.out::println);
        // Password must contain at least 1 digit characters.,Password must contain at least 1 special characters.
        assertEquals(1, constraintViolations.size());
        assertEquals(messages.iterator().next(), constraintViolations.iterator().next().getMessage());
    }
}

















