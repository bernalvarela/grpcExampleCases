package com.corunet.grpc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableWebMvc
public class GrpcApplication {

	public static void main(String[] args) {
		SpringApplication.run(GrpcApplication.class, args);
	}
}
