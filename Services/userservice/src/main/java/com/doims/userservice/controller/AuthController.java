package com.doims.userservice.controller;

import com.doims.userservice.dto.ApiResponse;
import com.doims.userservice.dto.RegisterRequest;
import com.doims.userservice.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<ApiResponse> registerUser(@Valid @RequestBody RegisterRequest registerRequest)
    {
       ApiResponse result=authService.register(registerRequest);
       if(!result.isSuccess())
       {
           return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
       }
       return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }




}
