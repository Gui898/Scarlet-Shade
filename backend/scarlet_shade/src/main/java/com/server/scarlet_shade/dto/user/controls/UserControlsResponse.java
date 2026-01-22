package com.server.scarlet_shade.dto.user.controls;

public record UserControlsResponse(
        ControlsDTO keyboard,
        ControlsDTO gamepad
    ) {
}
