package com.myapplication.payment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringApps {

	public static void main(String[] args) {
		SpringApplication springApplication = new SpringApplication(SpringApps.class);
		springApplication.run(args);
	}

}
