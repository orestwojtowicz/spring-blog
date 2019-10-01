package com.blog.demo.configuration.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * Created by damiass on Sep, 2019
 */
@Configuration
@EnableGlobalMethodSecurity(securedEnabled = true)
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private UserDetailServiceImpl userDetailService;


    public SecurityConfig(UserDetailServiceImpl userDetailService) {
        this.userDetailService = userDetailService;
    }
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // auth ma metode userDetailsService, ktora przyjmuje obiekt UserDetailService
        auth.userDetailsService(userDetailService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/h2/console").permitAll()
                .and()
                .formLogin().loginPage("/login").permitAll()
                .usernameParameter("email")
                .passwordParameter("password").and()
            .csrf().disable()
            .headers().frameOptions().disable();
    }



}
