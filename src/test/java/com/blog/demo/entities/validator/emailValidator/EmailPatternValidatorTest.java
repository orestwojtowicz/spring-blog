package com.blog.demo.entities.validator.emailValidator;

import com.blog.demo.entities.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.validation.ClockProvider;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by damiass on Oct, 2019
 */
class EmailPatternValidatorTest {

    // explanation -> https://regexr.com/

    private String regex = "^([_a-zA-Z0-9-]+(\\.[_a-zA-Z0-9-]+)*@[a-zA-Z-]+(\\.[a-zA-Z0-9-]+)*(\\.[a-zA-Z]{1,6}))?$";
    private Pattern pattern = Pattern.compile(regex);
    EmailPatternValidator emailPatternValidator = new EmailPatternValidator();
    ConstraintValidatorContext constraintValidatorContext = new ConstraintValidatorContext() {
        @Override
        public void disableDefaultConstraintViolation() {

        }

        @Override
        public String getDefaultConstraintMessageTemplate() {
            return null;
        }

        @Override
        public ClockProvider getClockProvider() {
            return null;
        }

        @Override
        public ConstraintViolationBuilder buildConstraintViolationWithTemplate(String s) {
            return null;
        }

        @Override
        public <T> T unwrap(Class<T> aClass) {
            return null;
        }
    };


    @BeforeEach
    void setUp() {
    }





    @Test
    void emailRegexAnnotationCorrectEmailTest() {
        Matcher matcher1 = pattern.matcher("good94@gmail.com");
        Matcher matcher2 = pattern.matcher("good@gmail.com");
        Matcher matcher3 = pattern.matcher("good12@gmail.com");
        assertTrue(matcher1.matches());
        assertTrue(matcher2.matches());
        assertTrue(matcher3.matches());
    }
    @Test
    void emailRegexAnnotationWrongEmailTest() {
        Matcher matcher1 = pattern.matcher("wrong@com12.pl");
        Matcher matcher2 = pattern.matcher("wrong@12com.pl");
        Matcher matcher3 = pattern.matcher("wrong12com.pl");
        Matcher matcher4 = pattern.matcher("wrong12compl");
        Matcher matcher5 = pattern.matcher("wrong12com.pl.");
        Matcher matcher6 = pattern.matcher("wrong12com,pl.");

        assertFalse(matcher1.matches());
        assertFalse(matcher2.matches());
        assertFalse(matcher3.matches());
        assertFalse(matcher4.matches());
        assertFalse(matcher5.matches());
        assertFalse(matcher6.matches());


    }
    @Test
    void isValidTest() {
        User user = new User();
        user.setEmail("123john@gmail.com");
        assertFalse(emailPatternValidator.isValid(user, constraintValidatorContext));

    }

}
