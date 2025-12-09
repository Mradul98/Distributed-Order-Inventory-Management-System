package com.doims.userservice.serviceImpl;

import com.doims.userservice.config.PasswordConfig;
import com.doims.userservice.dto.ApiResponse;
import com.doims.userservice.dto.RegisterRequest;
import com.doims.userservice.entity.UserEntity;
import com.doims.userservice.repository.UserRepository;
import com.doims.userservice.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {


    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    @Override
    public ApiResponse register(RegisterRequest registerRequest) {

        //checking is email already present
        if(userRepository.findByEmail(registerRequest.getEmail()).isPresent())
        {
            return new ApiResponse("Email Already Exists",false);
        }

        UserEntity user=new UserEntity();
        user.setName(registerRequest.getName());
        user.setEmail(registerRequest.getEmail());
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        userRepository.save(user);
        return new ApiResponse("User created successfully",true);

    }
}
