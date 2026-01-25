package com.server.scarlet_shade.dto.controls;

import jakarta.validation.constraints.NotEmpty;

public record ControlsAttributes(
        @NotEmpty String moveUp,
        @NotEmpty String moveDown,
        @NotEmpty String moveLeft,
        @NotEmpty String moveRight,
        @NotEmpty String jump,
        @NotEmpty String dash,
        @NotEmpty String crouch,
        @NotEmpty String attack,
        @NotEmpty String spinAttack,
        @NotEmpty String especialMoveOne,
        @NotEmpty String especialMoveTwo,
        @NotEmpty String menuAccess,
        @NotEmpty String selectItem,
        @NotEmpty String useItem
) {}