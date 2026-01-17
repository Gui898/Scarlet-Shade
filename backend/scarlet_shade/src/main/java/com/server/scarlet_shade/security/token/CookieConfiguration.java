package com.server.scarlet_shade.security.token;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.server.scarlet_shade.security.user.UserSecurity;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class CookieConfiguration {

    @Value("${jwt.token.expiration}")
    private int expiration;

    private final AuthenticationManager authenticationManager;
    private final TokenConfiguration tokenConfig;

    public void authCreateCookie(String username, String password, HttpServletResponse httpServletResponse) {

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, password);
        Authentication authentication = authenticationManager.authenticate(authenticationToken);

        UserSecurity userSecurity = (UserSecurity) authentication.getPrincipal();
        String token = tokenConfig.generateToken(userSecurity.getUser());
          
        ResponseCookie cookie = ResponseCookie
            .from("access_token", token)
            .httpOnly(true)
            .secure(false)           
            .sameSite("Lax")     
            .path("/")
            .maxAge(expiration)
            .build();

        httpServletResponse.addHeader(HttpHeaders.SET_COOKIE, cookie.toString());
    }

    public void authDeleteCookie(HttpServletResponse httpServletResponse) {

        ResponseCookie cookie = ResponseCookie
        .from("access_token", "")
        .httpOnly(true)
        .secure(false)
        .sameSite("Lax")
        .path("/")
        .maxAge(0)
        .build();

        httpServletResponse.addHeader(HttpHeaders.SET_COOKIE, cookie.toString());
    }
}