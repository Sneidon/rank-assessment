package com.rank.startup;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@EnableConfigurationProperties
@SpringBootApplication
public class CasinoStartUpApplication {
	public static void main(String[] args) {
		SpringApplication.run(CasinoStartUpApplication.class, args);
	}
}
