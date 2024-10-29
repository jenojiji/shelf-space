package com.jeno.shelf_space_system;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class ShelfSpaceSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShelfSpaceSystemApplication.class, args);
	}

}
