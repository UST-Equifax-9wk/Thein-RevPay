package com.revature.RevPay;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RevPayApplication {

	public static void main(String[] args) {
		SpringApplication.run(RevPayApplication.class, args);
		System.out.println("Server is running on 8080");
	}

}
