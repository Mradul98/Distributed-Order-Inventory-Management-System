package com.doims.userservice.serviceImpl;

import com.doims.userservice.config.PasswordConfig;
import com.doims.userservice.dto.ApiResponse;
import com.doims.userservice.dto.LoginRequest;
import com.doims.userservice.dto.LoginResponse;
import com.doims.userservice.dto.RegisterRequest;
import com.doims.userservice.entity.UserEntity;
import com.doims.userservice.exception.EmailAlreadyExistsException;
import com.doims.userservice.exception.InvalidCredentialsException;
import com.doims.userservice.exception.UserNotFoundException;
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
            throw new EmailAlreadyExistsException("Email already exists");
        }

        UserEntity user=new UserEntity();
        user.setName(registerRequest.getName());
        user.setEmail(registerRequest.getEmail());
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        userRepository.save(user);
        return new ApiResponse("User created successfully",true);

    }

    @Override
    public LoginResponse login(LoginRequest loginRequest) {
       UserEntity user= userRepository.findByEmail(loginRequest.getEmail()).
               orElseThrow(()->new UserNotFoundException("User not found with this email"));
       if(!passwordEncoder.matches(loginRequest.getPassword(),user.getPassword()))
       {
           throw new InvalidCredentialsException("Invalid email or password");
       }
        return new LoginResponse("Login successful",true);
    }
}
