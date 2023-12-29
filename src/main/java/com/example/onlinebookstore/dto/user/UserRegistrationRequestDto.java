package com.example.onlinebookstore.dto.user;

import com.example.onlinebookstore.validation.FieldMatch;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@FieldMatch
public class UserRegistrationRequestDto {
    @NotBlank
    @Email
    @Size(max = 25)
    private String email;
    @NotBlank
    @Size(min = 6, max = 20)
    private String password;
    @NotBlank
    @Size(min = 6, max = 20)
    private String repeatPassword;
    @NotBlank
    @Size(max = 25)
    private String firstName;
    @NotBlank
    @Size(max = 25)
    private String lastName;
    private String shippingAddress;
}
