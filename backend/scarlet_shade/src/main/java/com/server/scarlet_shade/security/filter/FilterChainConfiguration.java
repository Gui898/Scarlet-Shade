package com.server.scarlet_shade.security.filter;

import java.io.IOException;
import java.util.Optional;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.server.scarlet_shade.security.token.JWTUserData;
import com.server.scarlet_shade.security.token.TokenConfiguration;
import com.server.scarlet_shade.security.user.UserSecurity;
import com.server.scarlet_shade.security.user.UserSecurityService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class FilterChainConfiguration extends OncePerRequestFilter {

    private final TokenConfiguration tokenConfig;
    private final UserSecurityService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request,HttpServletResponse response,FilterChain filterChain) throws ServletException, IOException {

        String token = getTokenFromCookie(request);

        if (token != null) {
            
            Optional<JWTUserData> optionalUser = tokenConfig.validateToken(token);

            if (optionalUser.isPresent()) {
                
                JWTUserData userData = optionalUser.get();
                try {

                    UserSecurity userDetails = (UserSecurity) userDetailsService.loadUserByUsername(userData.username());

                    UsernamePasswordAuthenticationToken auth =
                        new UsernamePasswordAuthenticationToken(
                            userDetails, null,
                            userDetails.getAuthorities() 
                        );

                    SecurityContextHolder.getContext().setAuthentication(auth);
                } 
                catch (UsernameNotFoundException e) {

                    SecurityContextHolder.clearContext();
                }
            }
        }

        filterChain.doFilter(request, response);
    }


    private String getTokenFromCookie(HttpServletRequest request) {
        
        if (request.getCookies() == null) {
            return null;
        }

        for (Cookie cookie : request.getCookies()) {
            if ("access_token".equals(cookie.getName())) {
                
                return cookie.getValue();
            }
        }
        
        return null;
    }
}