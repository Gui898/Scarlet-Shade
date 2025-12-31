package com.server.scarlet_shade.controller;

import org.springframework.http.HttpStatus;

import com.server.scarlet_shade.dto.requests.UserLoginRequest;
import com.server.scarlet_shade.dto.requests.UserRegisterRequest;
import com.server.scarlet_shade.dto.responses.UserResponse;
import com.server.scarlet_shade.model.User;
import com.server.scarlet_shade.repository.UserRepository;
import com.server.scarlet_shade.security.TokenConfig;
import com.server.scarlet_shade.security.UserSecurity;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final TokenConfig tokenConfig;

    @PostMapping("/register")
    @Transactional
    public ResponseEntity<UserResponse> createUser(@RequestBody UserRegisterRequest userRegisterRequest){
        
        User user = new User(userRegisterRequest.username(),
            userRegisterRequest.email(),
            passwordEncoder.encode(userRegisterRequest.password()));

        userRepository.save(user);
        
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
            userRegisterRequest.username(), userRegisterRequest.password());

        Authentication authentication = authenticationManager.authenticate(authenticationToken);

        UserSecurity userSecurity = (UserSecurity) authentication.getPrincipal();
        String token = tokenConfig.generateToken(userSecurity.getUser());

        return ResponseEntity.status(HttpStatus.CREATED).body(new UserResponse(token));
    }

    @PostMapping("/login")
    public ResponseEntity<UserResponse> loginUser(@RequestBody UserLoginRequest userLoginRequest) {
        
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
            userLoginRequest.username(), userLoginRequest.password());

        Authentication authentication = authenticationManager.authenticate(authenticationToken);

        UserSecurity userSecurity = (UserSecurity) authentication.getPrincipal();
        String token = tokenConfig.generateToken(userSecurity.getUser());

        return ResponseEntity.status(HttpStatus.OK).body(new UserResponse(token));
    }
}