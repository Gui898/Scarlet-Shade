package com.server.scarlet_shade.auth;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.server.scarlet_shade.auth.dto.UserLoginRequest;
import com.server.scarlet_shade.auth.dto.UserRegisterRequest;
import com.server.scarlet_shade.auth.dto.UserResponse;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<UserResponse> createUser(@RequestBody @Valid UserRegisterRequest userRegisterRequest, HttpServletResponse httpServletResponse){

        UserResponse userResponse = 
            authService.authRegister(userRegisterRequest, httpServletResponse);

        return ResponseEntity.status(HttpStatus.CREATED).body(userResponse);
    }

    @PostMapping("/login")
    public ResponseEntity<UserResponse> loginUser(@RequestBody @Valid UserLoginRequest userLoginRequest, HttpServletResponse httpServletResponse) {
        
        UserResponse userResponse = 
            authService.authLogin(userLoginRequest, httpServletResponse);

        return ResponseEntity.status(HttpStatus.OK).body(userResponse);
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logoutUser(HttpServletResponse httpServletResponse) {

        authService.authLogout(httpServletResponse);

        return ResponseEntity.noContent().build();
    }  
}