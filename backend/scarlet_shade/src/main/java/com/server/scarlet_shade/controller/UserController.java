package com.server.scarlet_shade.controller;

import com.server.scarlet_shade.dto.requests.UserLoginRequest;
import com.server.scarlet_shade.dto.requests.UserRegisterRequest;
import com.server.scarlet_shade.dto.responses.UserResponse;
import com.server.scarlet_shade.model.User;
import com.server.scarlet_shade.repository.UserRepository;
import com.server.scarlet_shade.security.TokenConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
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
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final TokenConfig tokenConfig;

    @PostMapping("/register")
    @Transactional
    public ResponseEntity<UserResponse> createUser(@RequestBody UserRegisterRequest userRegisterRequest){
        User user = new User(userRegisterRequest.username(),
                             userRegisterRequest.email(),
                             passwordEncoder.encode(userRegisterRequest.password()));

        userRepository.save(user);
        UsernamePasswordAuthenticationToken userAndPass = new UsernamePasswordAuthenticationToken(user.getUsername(), userRegisterRequest.password());
        String token = tokenConfig.generateToken(user);
        return ResponseEntity.ok(new UserResponse(token));
    }

    @PostMapping("/login")
    public ResponseEntity<UserResponse> loginUser(@RequestBody UserLoginRequest userLoginRequest){
        User user = userRepository.findByUsername(userLoginRequest.username());
        UsernamePasswordAuthenticationToken userAndPass = new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword());
        String token = tokenConfig.generateToken(user);
        return ResponseEntity.ok(new UserResponse(token));
    }

}
