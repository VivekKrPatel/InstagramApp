package com.geekster.InstagramProject.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserSignUpInput {

    @NotBlank
    @Pattern(regexp = "^[A-Z][a-z]*$", message = "Name must start with an uppercase letter")
    private String firstName;
    private String lastName;

    @Min(value = 18,message = "Age should not be less than 18")
    private Integer age;

    @Email
    @NotEmpty
    private String email;

    @NotNull
    @Pattern(regexp = "[0-9]{10}")
    private String phoneNumber;

    @NotNull
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d\\S]{8,20}$",message = "Note : Password length between 8 and 20 characters.\n" +
            "At least one uppercase letter.\n" +
            "At least one lowercase letter.\n" +
            "At least one digit.\n" +
            "Can include special characters.")
    private String password;
}
