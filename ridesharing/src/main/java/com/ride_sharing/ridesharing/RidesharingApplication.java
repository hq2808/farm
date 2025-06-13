package com.ride_sharing.ridesharing;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class RidesharingApplication {

	public static void main(String[] args) {
		SpringApplication.run(RidesharingApplication.class, args);
	}

}
