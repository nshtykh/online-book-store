package com.example.onlinebookstore.service.user;

import com.example.onlinebookstore.dto.user.UserRegistrationRequestDto;
import com.example.onlinebookstore.dto.user.UserResponseDto;
import com.example.onlinebookstore.exception.RegistrationException;
import org.springframework.web.bind.annotation.RequestBody;

public interface UserService {
    UserResponseDto register(UserRegistrationRequestDto request);
}
