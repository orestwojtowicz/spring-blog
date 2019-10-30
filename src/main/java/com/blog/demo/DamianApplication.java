package com.blog.demo;

import org.ocpsoft.prettytime.PrettyTime;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.EnableTransactionManagement;



@EnableTransactionManagement
@SpringBootApplication
public class DamianApplication {

	public static void main(String[] args) {
		SpringApplication.run(DamianApplication.class, args);
	}

}
