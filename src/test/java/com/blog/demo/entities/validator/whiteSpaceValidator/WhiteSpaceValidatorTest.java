package com.blog.demo.entities.validator.whiteSpaceValidator;

import com.blog.demo.entities.User;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import javax.validation.*;
import javax.validation.constraints.NotNull;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by damiass on Oct, 2019
 */
@Tag("annotation")
class WhiteSpaceValidatorTest {


    private static Validator validator;
    private User user;


    @BeforeEach
    void setUp() {
        user = new User();
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    /*
    * __Nick
    * _Nick where _ is white space character (space)
    * Please remove white space in your user name and user name must start with character
    * */



    @DisplayName("White Character Test")
    @ParameterizedTest(name = " {displayName} - [{index}] {arguments}")
    @ValueSource(strings = {"    wrongNick", "   againwrong", "       wrong"})
    void whiteSpaceBeforeUserNameTest(String wrongNick) {
    user.setNick(wrongNick);
       Set<ConstraintViolation<String>> constraintValidators = validator.validate(user.getNick());
        constraintValidators.forEach(System.out::print);
        Set<ConstraintViolation<User>> constraintViolations = validator.validateProperty(user, "nick");
        assertEquals("Please remove white space in your user name and user name must start with character",
                constraintViolations.iterator().next().getMessage());
         assertEquals(1, constraintViolations.size());

    }
    @Test
    void numberBeforeUserNameTest() {
        user.setNick("123wrongNick");

        Set<ConstraintViolation<String>> constraintValidators = validator.validate(user.getNick());

        constraintValidators.forEach(System.out::print);
        Set<ConstraintViolation<User>> constraintViolations = validator.validateProperty(user, "nick");
        assertEquals("Please remove white space in your user name and user name must start with character",
                constraintViolations.iterator().next().getMessage());
        assertEquals(1, constraintViolations.size());

    }
    @Test
    void correctNickNameTest() {
        user.setNick("Okay");
        Set<ConstraintViolation<String>> constraintValidators = validator.validate(user.getNick());
        constraintValidators.forEach(System.out::print);
        Set<ConstraintViolation<User>> constraintViolations = validator.validateProperty(user, "nick");
        assertEquals(0, constraintViolations.size());

    }


}















