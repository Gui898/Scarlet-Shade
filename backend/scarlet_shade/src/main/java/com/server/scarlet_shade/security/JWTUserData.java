package com.server.scarlet_shade.security;

import lombok.Builder;
import lombok.NonNull;

@Builder
public record JWTUserData(@NonNull Long userId, @NonNull String username) {
}
