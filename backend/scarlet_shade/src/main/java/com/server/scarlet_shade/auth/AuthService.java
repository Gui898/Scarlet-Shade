package com.server.scarlet_shade.auth;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.server.scarlet_shade.auth.dto.UserLoginRequest;
import com.server.scarlet_shade.auth.dto.UserRegisterRequest;
import com.server.scarlet_shade.security.token.CookieConfiguration;
import com.server.scarlet_shade.service.UserService;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {
    
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final CookieConfiguration cookieConfig;

    public void authRegister(UserRegisterRequest userRegisterRequest, HttpServletResponse httpServletResponse) {

        userService.createUser(
            userRegisterRequest.username(), 
            userRegisterRequest.email(), 
            passwordEncoder.encode(userRegisterRequest.password()));

        cookieConfig.authCreateCookie(
            userRegisterRequest.username(),
            userRegisterRequest.password(),
            httpServletResponse
        );
    }

    public void authLogin(UserLoginRequest userLoginRequest, HttpServletResponse httpServletResponse) {
        
        cookieConfig.authCreateCookie(
            userLoginRequest.username(),
            userLoginRequest.password(),
            httpServletResponse
        );
    }

    public void authLogout(HttpServletResponse httpServletResponse) {

        cookieConfig.authDeleteCookie(httpServletResponse);
    }
}