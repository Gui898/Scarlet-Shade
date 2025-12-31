package com.server.scarlet_shade.model.controls;

import com.server.scarlet_shade.model.User;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "gamepad_control")
@Getter
@Setter
public class GamepadControls extends Controls {

    public GamepadControls(String moveUp, String moveDown, String moveLeft, String moveRight, String jump, String dash, String crouch, String attack, String spinAttack, String especialMoveOne, String especialMoveOTwo, String menuAccess, String selectItem, String useItem, User user) {
        super(moveUp, moveDown, moveLeft, moveRight, jump, dash, crouch, attack, spinAttack, especialMoveOne, especialMoveOTwo, menuAccess, selectItem, useItem, user);
    }

    public GamepadControls() {
        super("ANALOG_UP", "ANALOG_DOWN", "ANALOG_LEFT", "ANALOG_RIGHT", "A", "B", "ANALOG_CLICK", "RIGHT_TRIGGER", "LEFT_TRIGGER" , "X", "Y", "START", "RIGHT_BUMPER", "LEFT_BUMPER");
    }
}