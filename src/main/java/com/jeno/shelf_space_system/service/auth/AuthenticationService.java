package com.jeno.shelf_space_system.service.auth;

import com.jeno.shelf_space_system.dto.auth.AuthenticationRequest;
import com.jeno.shelf_space_system.dto.auth.AuthenticationResponse;
import com.jeno.shelf_space_system.dto.auth.RegistrationRequest;
import com.jeno.shelf_space_system.model.user.User;
import com.jeno.shelf_space_system.repository.RoleRepository;
import com.jeno.shelf_space_system.repository.UserRepository;
import com.jeno.shelf_space_system.security.JwtService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

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

    public AuthenticationResponse authenticate(@Valid AuthenticationRequest request) {

        var auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var claims = new HashMap<String, Objects>();
        var user = ((User) auth.getPrincipal());
//        claims.put("fullName",user.fullName());
        var jwtToken = jwtService.generateToken(claims, user);
        return AuthenticationResponse.builder().token(jwtToken).build();

    }
}
