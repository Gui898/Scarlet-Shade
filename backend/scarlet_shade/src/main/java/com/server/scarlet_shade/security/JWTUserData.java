package com.server.scarlet_shade.security;

import lombok.Builder;

@Builder
public record JWTUserData(Long userId, String username) {
}
