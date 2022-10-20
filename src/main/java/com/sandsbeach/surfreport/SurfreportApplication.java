package com.sandsbeach.surfreport;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class SurfreportApplication {

	public static void main(String[] args) {
		SpringApplication.run(SurfreportApplication.class, args);
	}

}
