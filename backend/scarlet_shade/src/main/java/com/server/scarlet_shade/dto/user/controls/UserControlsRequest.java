package com.server.scarlet_shade.dto.user.controls;

import com.server.scarlet_shade.model.controls.GamepadControls;
import com.server.scarlet_shade.model.controls.KeyboardControls;

public record UserControlsRequest(
        KeyboardControls keyboard,
        GamepadControls gamepad
) {
}
