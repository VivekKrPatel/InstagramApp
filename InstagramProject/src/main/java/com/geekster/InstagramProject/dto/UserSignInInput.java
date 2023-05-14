package com.geekster.InstagramProject.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserSignInInput {

    @Email
    @NotNull(message = "Kindly give regitered email address Only")
    private String email;

    @NotBlank(message = "Kindly give regitered password Only")
    private String password;

}
