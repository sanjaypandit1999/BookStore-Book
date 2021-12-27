package com.bridgelab.bookstore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class BookControllerApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookControllerApplication.class, args);
	}

}
