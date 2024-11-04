package com.jeno.shelf_space_system.dto.auth;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Builder
public class RegistrationRequest {
    @NotEmpty(message = "FirstName should not be empty")
    @NotBlank(message = "FirstName should not be blank")
    private String firstName;
    @NotEmpty(message = "LastName should not be empty")
    @NotBlank(message = "LastName should not be blank")
    private String lastName;
    @NotEmpty(message = "Email should not be empty")
    @NotBlank(message = "Email should not be blank")
    @Email(message = "Email is not in correct format")
    private String email;
    @NotEmpty(message = "Password should not be empty")
    @NotBlank(message = "Password should not be blank")
    @Size(min = 4, message = "Password should contain minimum 4 characters")
    private String password;
}
