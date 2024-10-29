package com.jeno.shelf_space_system.service.auth;

import com.jeno.shelf_space_system.dto.auth.RegistrationRequest;
import com.jeno.shelf_space_system.model.user.User;
import com.jeno.shelf_space_system.repository.RoleRepository;
import com.jeno.shelf_space_system.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    public void registerUser(RegistrationRequest request) {

        var userRole = roleRepository.findByName("USER")
                //to-do:better exception handling
                .orElseThrow(() -> new IllegalStateException("ROLE:USER not initialized"));

        User user = User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .accountLocked(false)
                .enabled(false)
                .roles(List.of(userRole))
                .build();

        userRepository.save(user);

    }
}
