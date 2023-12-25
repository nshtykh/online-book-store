package com.example.onlinebookstore.security;

import com.example.onlinebookstore.dto.user.UserLoginRequestDto;
import com.example.onlinebookstore.dto.user.UserLoginResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
    @Autowired
    private JwtUtil jwtUtil;
    public UserLoginResponseDto authenticate(UserLoginRequestDto request) {
        String token = jwtUtil.generateToken(request.email());
        return new UserLoginResponseDto(token);
    }
}
