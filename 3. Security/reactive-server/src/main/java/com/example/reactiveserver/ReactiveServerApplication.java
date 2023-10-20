package com.example.reactiveserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.reactive.config.EnableWebFlux;

@SpringBootApplication
@EnableWebFlux
public class ReactiveServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReactiveServerApplication.class, args);
	}

}
