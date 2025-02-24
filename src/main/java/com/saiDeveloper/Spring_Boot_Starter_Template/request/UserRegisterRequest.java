package com.saiDeveloper.Spring_Boot_Starter_Template.request;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRegisterRequest {

    @Size(min=3, max =50, message = "First name must be between 3 and 50 characters")
    private String firstName;
    private String lastName;
    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", message = "Invalid email format")
    private String email;
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$", message = "Ensure your password contains at least 8 characters, including one uppercase letter, one lowercase letter, one digit, and one special character, with no spaces")
    private String password;
    @Pattern(regexp = "^[0-9]{10}$", message = "Invalid mobile number format")
    private String mobile;
    @Pattern(regexp = "^(STUDENT|ADMIN)$", message = "Invalid role")
    private String role;
}
