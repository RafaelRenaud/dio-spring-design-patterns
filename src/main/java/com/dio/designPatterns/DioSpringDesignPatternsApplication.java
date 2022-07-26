package com.dio.designPatterns;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class DioSpringDesignPatternsApplication {

	public static void main(String[] args) {
		SpringApplication.run(DioSpringDesignPatternsApplication.class, args);
	}

}
