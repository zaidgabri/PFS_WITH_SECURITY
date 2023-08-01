package com.Sk09Team.Consultation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.cloud.openfeign.EnableFeignClients;

import java.util.Arrays;

@EnableFeignClients

@SpringBootApplication
public class ConsultationApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConsultationApplication.class, args);
	}

}
