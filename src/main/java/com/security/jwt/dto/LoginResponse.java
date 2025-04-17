package com.security.jwt.dto;

import com.security.jwt.entity.User;
import lombok.Data;

@Data
public class LoginResponse {
    private User user;
    private String jwtToken;
}
