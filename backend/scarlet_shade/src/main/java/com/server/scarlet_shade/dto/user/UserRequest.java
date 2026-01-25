package com.server.scarlet_shade.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record UserRequest(

    @NotBlank
    @Size(min = 10, max = 20, message = "Username must be between 10 and 20 caracters")
    String username, 
    
    @NotBlank
    @Email(message = "This is not an E-Mail")
    String email,
    
    @Pattern(
        regexp = "^$|^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).{8,}$",
        message = "Password must contain at least 8 characters, one uppercase letter, one lowercase letter and one number"
    )
    String password
) {}