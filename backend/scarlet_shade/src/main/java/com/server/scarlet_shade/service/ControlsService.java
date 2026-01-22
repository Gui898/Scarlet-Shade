package com.server.scarlet_shade.service;

import com.server.scarlet_shade.dto.user.controls.ControlsDTO;
import com.server.scarlet_shade.dto.user.controls.UserControlsRequest;
import com.server.scarlet_shade.dto.user.controls.UserControlsResponse;
import com.server.scarlet_shade.exception.controls.ControlsNotFound;
import com.server.scarlet_shade.model.User;
import com.server.scarlet_shade.model.controls.Controls;
import com.server.scarlet_shade.model.controls.GamepadControls;
import com.server.scarlet_shade.model.controls.KeyboardControls;
import com.server.scarlet_shade.repository.controls.GamepadControlsRepository;
import com.server.scarlet_shade.repository.controls.KeyboardControlsRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ControlsService {

    private final GamepadControlsRepository gamepadControlsRepository;
    private final KeyboardControlsRepository keyboardControlsRepository;

    @Transactional
    public UserControlsResponse getControlsByUser(User user){
        Controls kc = keyboardControlsRepository.getKeyboardControl(user.getId());
        Controls gc = gamepadControlsRepository.getGamepadControl(user.getId());

        if(kc == null || gc == null){
            throw new ControlsNotFound();
        }

        ControlsDTO keyboard = new ControlsDTO(
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

        ControlsDTO gamepad = new ControlsDTO(
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

        return new UserControlsResponse(keyboard, gamepad);
    }

    @Transactional
    public void updateControls(UserControlsRequest controls, User user){
        KeyboardControls keyboard = keyboardControlsRepository.getKeyboardControl(user.getId());

        GamepadControls gamepad = gamepadControlsRepository.getGamepadControl(user.getId());

        KeyboardControls kc = controls.keyboard();
        GamepadControls gc = controls.gamepad();

        // Keyboard
        keyboard.setMoveUp(kc.getMoveUp());
        keyboard.setMoveDown(kc.getMoveDown());
        keyboard.setMoveLeft(kc.getMoveLeft());
        keyboard.setMoveRight(kc.getMoveRight());
        keyboard.setJump(kc.getJump());
        keyboard.setDash(kc.getDash());
        keyboard.setCrouch(kc.getCrouch());
        keyboard.setAttack(kc.getAttack());
        keyboard.setSpinAttack(kc.getSpinAttack());
        keyboard.setEspecialMoveOne(kc.getEspecialMoveOne());
        keyboard.setEspecialMoveTwo(kc.getEspecialMoveTwo());
        keyboard.setMenuAccess(kc.getMenuAccess());
        keyboard.setSelectItem(kc.getSelectItem());
        keyboard.setUseItem(kc.getUseItem());

        // Gamepad
        gamepad.setMoveUp(gc.getMoveUp());
        gamepad.setMoveDown(gc.getMoveDown());
        gamepad.setMoveLeft(gc.getMoveLeft());
        gamepad.setMoveRight(gc.getMoveRight());
        gamepad.setJump(gc.getJump());
        gamepad.setDash(gc.getDash());
        gamepad.setCrouch(gc.getCrouch());
        gamepad.setAttack(gc.getAttack());
        gamepad.setSpinAttack(gc.getSpinAttack());
        gamepad.setEspecialMoveOne(gc.getEspecialMoveOne());
        gamepad.setEspecialMoveTwo(gc.getEspecialMoveTwo());
        gamepad.setMenuAccess(gc.getMenuAccess());
        gamepad.setSelectItem(gc.getSelectItem());
        gamepad.setUseItem(gc.getUseItem());

        keyboardControlsRepository.save(keyboard);
        gamepadControlsRepository.save(gamepad);
    }

}
