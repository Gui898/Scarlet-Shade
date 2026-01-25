package com.server.scarlet_shade.dto.controls;

import jakarta.validation.constraints.NotNull;

public record ControlsRequestResponse(
        
    @NotNull ControlsAttributes keyboard,
    @NotNull ControlsAttributes gamepad
) {}