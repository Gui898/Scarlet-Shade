package com.server.scarlet_shade.auth;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.server.scarlet_shade.auth.dto.UserLoginRequest;
import com.server.scarlet_shade.auth.dto.UserRegisterRequest;
import com.server.scarlet_shade.auth.dto.UserResponse;
import com.server.scarlet_shade.model.User;
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

    public UserResponse authRegister(UserRegisterRequest userRegisterRequest, HttpServletResponse httpServletResponse) {

        SecurityContextHolder.clearContext();

        User user = userService.createUser(
            userRegisterRequest.username(), 
            userRegisterRequest.email(), 
            passwordEncoder.encode(userRegisterRequest.password()));

        cookieConfig.authCreateCookie(
            userRegisterRequest.username(),
            userRegisterRequest.password(),
            httpServletResponse
        );

        return userService.getUserResponse(user);
    }

    public UserResponse authLogin(UserLoginRequest userLoginRequest, HttpServletResponse httpServletResponse) {

        SecurityContextHolder.clearContext();
        
        User user = cookieConfig.authCreateCookie(
            userLoginRequest.username(),
            userLoginRequest.password(),
            httpServletResponse
        );

        return userService.getUserResponse(user);
    }

    public void authLogout(HttpServletResponse httpServletResponse) {

        cookieConfig.authDeleteCookie(httpServletResponse);
    }
}