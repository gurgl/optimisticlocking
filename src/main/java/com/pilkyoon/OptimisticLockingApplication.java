package com.pilkyoon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class OptimisticLockingApplication {

	public static void main(String[] args) {
		SpringApplication.run(OptimisticLockingApplication.class, args);
	}

}
