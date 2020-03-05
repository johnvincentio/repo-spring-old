package com.idc.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class SpringbootJpaApplication {

	public static void main(String[] args) {
		SpringApplication springApplication = new SpringApplication();
		System.out.println("--- main 6");
		ApplicationContext ctx = springApplication.run(SpringbootJpaApplication.class, args);
	}
}
