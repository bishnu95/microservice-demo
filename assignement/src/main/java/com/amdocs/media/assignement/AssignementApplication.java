package com.amdocs.media.assignement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;

@SpringBootApplication
@EnableHystrix
public class AssignementApplication {

	public static void main(String[] args) {
		SpringApplication.run(AssignementApplication.class, args);
	}

}
