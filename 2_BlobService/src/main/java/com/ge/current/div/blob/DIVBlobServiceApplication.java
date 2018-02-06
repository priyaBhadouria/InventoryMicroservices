package com.ge.current.div.blob;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@EnableAutoConfiguration
@SpringBootApplication
public class DIVBlobServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(DIVBlobServiceApplication.class, args);
	}
}
