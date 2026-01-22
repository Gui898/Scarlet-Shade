package com.server.scarlet_shade.dto.user.controls;

public record ControlsDTO(
        String moveUp,
        String moveDown,
        String moveLeft,
        String moveRight,
        String jump,
        String dash,
        String crouch,
        String attack,
        String spinAttack,
        String especialMoveOne,
        String especialMoveTwo,
        String menuAccess,
        String selectItem,
        String useItem
    ) {
}
