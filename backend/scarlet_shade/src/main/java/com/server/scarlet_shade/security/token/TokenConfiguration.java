package com.server.scarlet_shade.security.token;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.server.scarlet_shade.model.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Optional;

@Component
public class TokenConfiguration {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.token.expiration}")
    private int expiration;

    @Value("${spring.application.name}")
    private String serverName;

    private Algorithm algorithm;

    public String generateToken(User user){

        try {
            this.algorithm = Algorithm.HMAC256(secret);

            return JWT.create()
                    .withClaim("userId", user.getId())
                    .withIssuer(serverName)
                    .withSubject(user.getUsername())
                    .withExpiresAt(Instant.now().plusSeconds(expiration))
                    .withIssuedAt(Instant.now())
                    .sign(algorithm);
        }
        catch (JWTCreationException e) {
            
            throw e;
        }
    }

    public Optional<JWTUserData> validateToken(String token){
        
        try{
            this.algorithm = Algorithm.HMAC256(secret);

            DecodedJWT decode = JWT.require(algorithm)
                .withIssuer(serverName)
                .build()
                .verify(token);

            return Optional.of(JWTUserData.builder()
                .userId(decode.getClaim("userId").asLong())
                .username(decode.getSubject()).build());
        }
        catch (JWTVerificationException e){
            
            return Optional.empty();
        }
    }
}