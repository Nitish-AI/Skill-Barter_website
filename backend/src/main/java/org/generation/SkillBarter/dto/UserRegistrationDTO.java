// --- UserRegistrationDTO.java ---
// This DTO will be used to receive registration data from the client.
// It includes validation annotations.
package org.generation.SkillBarter.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Pattern;
import lombok.Data; // Lombok annotation to generate getters, setters, equals, hashCode, and toString

@Data
public class UserRegistrationDTO {

    @NotBlank(message = "First name is required")
    @Size(min = 2, max = 50, message = "First name must be between 2 and 50 characters")
    private String firstName;

    @NotBlank(message = "Username is required")
    @Size(min = 3, max = 50, message = "Username must be between 3 and 50 characters")
    private String userName;

    private String lastName; // Optional, no @NotBlank

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    @Size(max = 100, message = "Email cannot exceed 100 characters")
    private String email;

    @NotBlank(message = "Password is required")
    @Size(min = 8, message = "Password must be at least 8 characters long")
    // Regex for: at least one digit, one lowercase, one uppercase, one special character
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=(.*[A-Z]){1,})(?=(.*[!@#$%^&*_+-=]){1,}).{8,}$",
            message = "Password must contain at least one digit, one lowercase, one uppercase, and one special character")
    private String password;

    private String bio; // Optional
    private String address; // Optional
}
