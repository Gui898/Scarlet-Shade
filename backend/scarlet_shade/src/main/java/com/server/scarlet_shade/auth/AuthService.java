package com.server.scarlet_shade.auth;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.server.scarlet_shade.auth.dto.UserLoginRequest;
import com.server.scarlet_shade.auth.dto.UserRegisterRequest;
import com.server.scarlet_shade.model.User;
import com.server.scarlet_shade.repository.UserRepository;
import com.server.scarlet_shade.security.token.CookieConfig;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {
    
    public final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final CookieConfig cookieConfig;

    @Transactional
    public void authRegister(UserRegisterRequest userRegisterRequest, HttpServletResponse httpServletResponse) {

        User user = new User(
            userRegisterRequest.username(),
            userRegisterRequest.email(),
            passwordEncoder.encode(userRegisterRequest.password())
        );

        userRepository.save(user);

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