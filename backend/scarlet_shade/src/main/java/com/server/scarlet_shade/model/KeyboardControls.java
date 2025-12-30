package com.server.scarlet_shade.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "keyboard_control")
public class KeyboardControls extends Controls{

    public KeyboardControls(String moveUp, String moveDown, String moveLeft, String moveRight, String jump, String dash, String crouch, String attack, String spinAttack, String especialMoveOne, String especialMoveOTwo, String menuAccess, String selectItem, String useItem, User user) {
        super(moveUp, moveDown, moveLeft, moveRight, jump, dash, crouch, attack, spinAttack, especialMoveOne, especialMoveOTwo, menuAccess, selectItem, useItem, user);
    }
}
