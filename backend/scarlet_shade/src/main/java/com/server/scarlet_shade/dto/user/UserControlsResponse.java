package com.server.scarlet_shade.dto.user;

public record UserControlsResponse(
        ControlsDTO keyboard,
        ControlsDTO gamepad
) {
}
