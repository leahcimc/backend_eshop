package com.FSSE2309.backend_eshop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
		//(exclude = {org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration.class})
public class BackendEshopApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackendEshopApplication.class, args);
	}

}
