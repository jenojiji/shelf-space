package com.jeno.shelf_space_system;

import com.jeno.shelf_space_system.model.user.Role;
import com.jeno.shelf_space_system.repository.RoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class ShelfSpaceSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShelfSpaceSystemApplication.class, args);

    }

    @Bean
    public CommandLineRunner runner(RoleRepository roleRepository) {
        return args -> {
            if (roleRepository.findByName("USER").isEmpty()) {
                roleRepository.save(Role.builder().name("USER").build());
            }
        };
    }

}
