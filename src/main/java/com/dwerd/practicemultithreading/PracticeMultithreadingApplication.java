package com.dwerd.practicemultithreading;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.dwerd.controller")

public class PracticeMultithreadingApplication {

	public static void main(String[] args) {
		SpringApplication.run(PracticeMultithreadingApplication.class, args);
	}

}
