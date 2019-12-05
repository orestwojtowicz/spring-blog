# spring-blog


[![versionJava](https://img.shields.io/badge/jdk-11-brightgreen.svg?logo=java)](https://openjdk.java.net)
[![versionSpring](https://img.shields.io/badge/Spring-2.2.1-brightgreen)](https://spring.io/)
[![mavenVersion](https://img.shields.io/badge/Maven-3.6.1-brightgreen)](https://maven.apache.org/)
[![versionThymeleaf](https://img.shields.io/badge/thymeleaf-3.011-brightgreen)](https://www.thymeleaf.org/)
[![versionSecurity](https://img.shields.io/badge/security-5.16-brightgreen)](https://spring.io/projects/spring-security/)
[![versionJUnit](https://img.shields.io/badge/jUnit-5.3.1-brightgreen)](https://junit.org/junit5//)
[![versionLombok](https://img.shields.io/badge/lombok-1.18.8-brightgreen)](https://junit.org/junit5//)
[![versionMockito](https://img.shields.io/badge/mockito-2.23-brightgreen)](https://site.mockito.org/)


### About Application
##
I have always wanted to create blog application, so here comes my spring blog app. In my project I decided to use thymeleaf instead of creating REST application
and connect it with Angular because I found it more consisable but as new ideas come to my head and more lines of code have been added, this approach has some weakenss.
First of all, it would be more clean design to make it full stack application but on the other hand, such a solution takes much more time to develop and there is more places for mistakes.
On the other hand, it is much more easier to deliver only spring-boot app and also some experience in thymeleaf is nice to have in my opinion.
##

### App Features

Application covers core features of blog:
* User registration
* Reset password feature
* Forgotten password feature
* User login/log out
* User profile 
* User avatars (every user has his own profile picture, which can be changed)
* Admin has ability to create new post, add image to it, title and post content
* Every post has comments section, where only logged users can add new comments
* Each post has his own comment size feature, post creation date
* Admin has ability to delete any comment which is realeted to single post
##
### UserRegistration

After successfully registartion, email with activation link is being sent to user. When account is activated,
welcome email is presented to user.
![](https://raw.githubusercontent.com/orestwojtowicz/spring-blog/develop/src/main/resources/static/gifs/registration.gif)
##
### Add New Post

Add post feature is only available for admin. We have ability to customize font and also add custom images or format
code.
![](https://raw.githubusercontent.com/orestwojtowicz/spring-blog/develop/src/main/resources/static/gifs/add_post.gif)
##

### Search

Each post has his own topic, so we can filter then as we want to. Filtering is implementing in JavaScript and it is 
reading each value from input and compare it with each of list element.

![](https://raw.githubusercontent.com/orestwojtowicz/spring-blog/develop/src/main/resources/static/gifs/search.gif)
##

### Mobile Friendly

Application works fine on all mobile devices

![](https://raw.githubusercontent.com/orestwojtowicz/spring-blog/develop/src/main/resources/static/gifs/mobile.gif)
##

### Validation

Custom annotation has been made for user email, password and white space.
```
public class EmailPatternValidator implements ConstraintValidator<IEmailPattern, User> {

    String regex = "^([_a-zA-Z0-9-]+(\\.[_a-zA-Z0-9-]+)*@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*(\\.[a-zA-Z]{1,6}))?$";
    Pattern pattern = Pattern.compile(regex);

    @Override
    public boolean isValid(User user, ConstraintValidatorContext constraintValidatorContext) {

        if(Character.isDigit(user.getEmail().charAt(0))) return false;

        Matcher matcher = pattern.matcher(user.getEmail());
        return matcher.matches();
    }

    @Override
    public void initialize(IEmailPattern constraintAnnotation) {

    }
}

```
### Test for password

```
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

```

### Password strength is implemented via Passay library

```
 @Override
    public boolean isValid(String password, ConstraintValidatorContext context) {
        PasswordValidator validator = new PasswordValidator(Arrays.asList(
                new LengthRule(6, 100),
                new UppercaseCharacterRule(1),
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

```
### Test for password strength (sample)

```
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

```


![](https://raw.githubusercontent.com/orestwojtowicz/spring-blog/develop/src/main/resources/static/gifs/validation.gif)
##


### Reset Password

Each user has separate field in database for storing lost password. If user email is found, reset password token is created and sent to user email.
Token expiration is 24 hours. After clicking reset password link from email, old password is updated with new one with query:

```

   @Modifying
   @Query("update User u set u.password = :password where u.id = :id")
   void updatePassword(@Param("password") String password, @Param("id") Long id);
   
```
After successful operation, reset password is being deleted and only new password is storing in the database at the moment.













