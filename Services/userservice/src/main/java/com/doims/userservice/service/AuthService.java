package com.doims.userservice.service;

import com.doims.userservice.dto.ApiResponse;
import com.doims.userservice.dto.LoginRequest;
import com.doims.userservice.dto.LoginResponse;
import com.doims.userservice.dto.RegisterRequest;

public interface AuthService {

    ApiResponse register(RegisterRequest registerRequest);
    LoginResponse login(LoginRequest loginRequest);


}
