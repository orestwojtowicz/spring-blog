package com.blog.demo.services;

import com.blog.demo.entities.Role;
import com.blog.demo.entities.User;
import com.blog.demo.repositories.RoleRepository;
import com.blog.demo.repositories.UserRepository;
import com.blog.demo.utills.RandomPublicUserID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Java6BDDAssertions.then;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

/**
 * Created by damiass on Oct, 2019
 */

@ExtendWith(MockitoExtension.class)
class UserServiceTest {
    @Mock
    private UserRepository userRepository;

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    @Mock
    private RandomPublicUserID randomPublicUserID;
    @Mock
    private RoleRepository roleRepository;

    @InjectMocks
    private UserService userService;

    @Mock
    EmailSenderService emailSenderService;

    private String password = "password";
    private User user;
    private final String secret = "{bcrypt}" + encoder.encode(password);
    private static final String userEmail = "user@gmail.com";
    private static boolean isEnabled = false;


    @BeforeEach
    void setUp() {
        Role role = new Role();
        role.setName("USER_ROLE");
        randomPublicUserID = new RandomPublicUserID();

        user = new User();
        // user configuration
        user.setPassword(secret);
        user.setConfirmPassword(secret);
        user.addRole(role);
        user.setEmail(userEmail);
        String publicID = randomPublicUserID.generateRandomBytes();
        user.setPublicUserID(publicID);
        user.setEnabled(isEnabled);
        user.setId(1L);
        user.setNick("nick");

    }

    @Test
    void registerNewUser() {

        assertAll("User Properties Test",
                () -> assertEquals("user@gmail.com", user.getEmail()),
                () -> assertEquals(user.getPassword(), user.getConfirmPassword()),
                () -> assertEquals(user.getPublicUserID().length(), 22));

        when(userRepository.save(any(User.class))).thenReturn(user);
        when(roleRepository.findByName(any(String.class))).thenReturn(any());

        Role role = new Role();
        role.setName("ROLE_USER");
        user.addRole(role);

        emailSenderService.sendActivationEmail(user);
        verify(emailSenderService, times(1)).sendActivationEmail(user);
        User savedUser = userRepository.save(user);
        assertNotNull(savedUser);

        User registeredUser = userService.registerNewUser(user);
        assertNotNull(registeredUser);

    }
// https://reflectoring.io/unit-testing-spring-boot/
    @Test
    void setActivationCodeAndSendAndEmail() {
        String pass = "lajdos";
        final String newPassword = "{bcrypt}" + encoder.encode(pass);

        when(userRepository.findByEmail(any(String.class))).thenReturn(Optional.of(user));
        Optional<User> userEmail = userRepository.findByEmail(user.getEmail());
        User returnUser = userEmail.get();
        assertNotNull(returnUser);
        assertNotNull(userEmail.get());
        returnUser.setResetPasswordToken(UUID.randomUUID().toString());
        returnUser.setPasswordForChange(newPassword);
        emailSenderService.sendResetPasswordEmail(returnUser);

        assertNotNull(returnUser.getResetPasswordToken());
        verify(emailSenderService, times(1)).sendResetPasswordEmail(returnUser);
        assertNotEquals(user.getPassword(), returnUser.getPasswordForChange());

    }

}





















