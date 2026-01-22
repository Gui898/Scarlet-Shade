package com.server.scarlet_shade.model.controls;

import com.server.scarlet_shade.model.User;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "keyboard_control")
@Getter
@Setter
public class KeyboardControls extends Controls {

    public KeyboardControls(String moveUp, String moveDown, String moveLeft, String moveRight, String jump, String dash, String crouch, String attack, String spinAttack, String especialMoveOne, String especialMoveTwo, String menuAccess, String selectItem, String useItem, User user) {
        super(moveUp, moveDown, moveLeft, moveRight, jump, dash, crouch, attack, spinAttack, especialMoveOne, especialMoveTwo, menuAccess, selectItem, useItem, user);
    }

    public KeyboardControls() {
        super("W", "S", "A", "D", "SPACE", "SHIFT", "CONTROL", "J", "K", "N", "M", "ESC", "Q", "E");
    }
}