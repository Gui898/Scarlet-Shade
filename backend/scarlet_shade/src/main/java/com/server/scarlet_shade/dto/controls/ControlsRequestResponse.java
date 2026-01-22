package com.server.scarlet_shade.dto.controls;

public record ControlsRequestResponse(
        
    ControlsAttributes keyboard,
    ControlsAttributes gamepad
) {}