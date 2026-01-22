package com.server.scarlet_shade.service;

import org.springframework.stereotype.Service;

import com.server.scarlet_shade.dto.controls.ControlsAttributes;
import com.server.scarlet_shade.dto.controls.ControlsRequestResponse;
import com.server.scarlet_shade.exception.controls.ControlsNotFound;
import com.server.scarlet_shade.model.User;
import com.server.scarlet_shade.model.controls.Controls;
import com.server.scarlet_shade.model.controls.GamepadControls;
import com.server.scarlet_shade.model.controls.KeyboardControls;
import com.server.scarlet_shade.repository.controls.GamepadControlsRepository;
import com.server.scarlet_shade.repository.controls.KeyboardControlsRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ControlsService {

    private final GamepadControlsRepository gamepadControlsRepository;
    private final KeyboardControlsRepository keyboardControlsRepository;

    @Transactional
    public ControlsRequestResponse getControlsByUser(User user){
        Controls kc = keyboardControlsRepository.getKeyboardControl(user.getId());
        Controls gc = gamepadControlsRepository.getGamepadControl(user.getId());

        if(kc == null || gc == null){
            throw new ControlsNotFound();
        }

        ControlsAttributes keyboard = new ControlsAttributes(
                kc.getMoveUp(),
                kc.getMoveDown(),
                kc.getMoveLeft(),
                kc.getMoveRight(),
                kc.getJump(),
                kc.getDash(),
                kc.getCrouch(),
                kc.getAttack(),
                kc.getSpinAttack(),
                kc.getEspecialMoveOne(),
                kc.getEspecialMoveTwo(),
                kc.getMenuAccess(),
                kc.getSelectItem(),
                kc.getUseItem()
        );

        ControlsAttributes gamepad = new ControlsAttributes(
                gc.getMoveUp(),
                gc.getMoveDown(),
                gc.getMoveLeft(),
                gc.getMoveRight(),
                gc.getJump(),
                gc.getDash(),
                gc.getCrouch(),
                gc.getAttack(),
                gc.getSpinAttack(),
                gc.getEspecialMoveOne(),
                gc.getEspecialMoveTwo(),
                gc.getMenuAccess(),
                gc.getSelectItem(),
                gc.getUseItem()
        );

        return new ControlsRequestResponse(keyboard, gamepad);
    }

    @Transactional
    public void updateControls(ControlsRequestResponse controls, User user){
        
        KeyboardControls keyboard = user.getKeyboardControls();
        GamepadControls gamepad = user.getGamepadControls();

        ControlsAttributes kc = controls.keyboard();
        ControlsAttributes gc = controls.gamepad();

        // Keyboard
        keyboard.setMoveUp(kc.moveUp());
        keyboard.setMoveDown(kc.moveDown());
        keyboard.setMoveLeft(kc.moveLeft());
        keyboard.setMoveRight(kc.moveRight());
        keyboard.setJump(kc.jump());
        keyboard.setDash(kc.dash());
        keyboard.setCrouch(kc.crouch());
        keyboard.setAttack(kc.attack());
        keyboard.setSpinAttack(kc.spinAttack());
        keyboard.setEspecialMoveOne(kc.especialMoveOne());
        keyboard.setEspecialMoveTwo(kc.especialMoveTwo());
        keyboard.setMenuAccess(kc.menuAccess());
        keyboard.setSelectItem(kc.selectItem());
        keyboard.setUseItem(kc.useItem());

        // Gamepad
        gamepad.setMoveUp(gc.moveUp());
        gamepad.setMoveDown(gc.moveDown());
        gamepad.setMoveLeft(gc.moveLeft());
        gamepad.setMoveRight(gc.moveRight());
        gamepad.setJump(gc.jump());
        gamepad.setDash(gc.dash());
        gamepad.setCrouch(gc.crouch());
        gamepad.setAttack(gc.attack());
        gamepad.setSpinAttack(gc.spinAttack());
        gamepad.setEspecialMoveOne(gc.especialMoveOne());
        gamepad.setEspecialMoveTwo(gc.especialMoveTwo());
        gamepad.setMenuAccess(gc.menuAccess());
        gamepad.setSelectItem(gc.selectItem());
        gamepad.setUseItem(gc.useItem());

        keyboardControlsRepository.save(keyboard);
        gamepadControlsRepository.save(gamepad);
    }
}