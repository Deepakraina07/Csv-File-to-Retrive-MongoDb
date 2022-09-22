package com.samco;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CsvFileToMongodbApplication {

	public static void main(String[] args) {
		SpringApplication.run(CsvFileToMongodbApplication.class, args);
		System.out.println("Server Created Successfully");
	}

}
