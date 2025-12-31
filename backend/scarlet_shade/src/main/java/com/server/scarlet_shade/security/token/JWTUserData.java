package com.server.scarlet_shade.security.token;

import lombok.Builder;
import lombok.NonNull;

@Builder
public record JWTUserData(@NonNull Long userId, @NonNull String username) {
}
