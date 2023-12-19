package com.example.onlinebookstore.dto.user;

import lombok.Data;

@Data
public class UserDto {
    private Long id;
    private String email;
    private String firstname;
    private String lastName;
    private String shippingAddress;
}
