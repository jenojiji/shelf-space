package com.jeno.shelf_space_system.controller.auth;

import com.jeno.shelf_space_system.dto.auth.AuthenticationRequest;
import com.jeno.shelf_space_system.dto.auth.AuthenticationResponse;
import com.jeno.shelf_space_system.dto.auth.RegistrationRequest;
import com.jeno.shelf_space_system.service.auth.AuthenticationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    //Register Controller
    @PostMapping("/register")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<?> registerUser(@RequestBody @Valid RegistrationRequest registrationRequest) {
        authenticationService.registerUser(registrationRequest);
        return ResponseEntity.accepted().build();
    }

    //Authenticate user
    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticateUser(
            @RequestBody @Valid AuthenticationRequest request) {
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }


}
