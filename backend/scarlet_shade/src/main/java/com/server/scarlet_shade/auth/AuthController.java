package com.server.scarlet_shade.auth;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.server.scarlet_shade.auth.dto.UserLoginRequest;
import com.server.scarlet_shade.auth.dto.UserRegisterRequest;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<Void> createUser(@RequestBody UserRegisterRequest userRegisterRequest, HttpServletResponse httpServletResponse){

        authService.authRegister(userRegisterRequest, httpServletResponse);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/login")
    public ResponseEntity<Void> loginUser(@RequestBody UserLoginRequest userLoginRequest, HttpServletResponse httpServletResponse) {
        
        authService.authLogin(userLoginRequest, httpServletResponse);

        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logoutUser(HttpServletResponse httpServletResponse) {

        authService.authLogout(httpServletResponse);

        return ResponseEntity.noContent().build();
    }  
}