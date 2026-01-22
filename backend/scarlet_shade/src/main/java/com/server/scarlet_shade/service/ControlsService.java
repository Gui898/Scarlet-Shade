package com.server.scarlet_shade.service;

import com.server.scarlet_shade.dto.user.ControlsDTO;
import com.server.scarlet_shade.dto.user.UserControlsResponse;
import com.server.scarlet_shade.exception.controls.ControlsNotFound;
import com.server.scarlet_shade.model.User;
import com.server.scarlet_shade.model.controls.Controls;
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

        ControlsDTO keyboard = new ControlsDTO(kc.getMoveUp(),
                kc.getMoveDown()
                , kc.getMoveLeft(),
                kc.getMoveRight(),
                kc.getJump(),
                kc.getDash(),
                kc.getCrouch(),
                kc.getAttack(),
                kc.getSpinAttack(),
                kc.getEspecialMoveOne(),
                kc.getEspecialMoveOTwo(),
                kc.getMenuAccess(),
                kc.getSelectItem(),
                kc.getUseItem()
        );

        ControlsDTO gamepad = new ControlsDTO(gc.getMoveUp(),
                gc.getMoveDown(),
                gc.getMoveLeft(),
                gc.getMoveRight(),
                gc.getJump(),
                gc.getDash(),
                gc.getCrouch(),
                gc.getAttack(),
                gc.getSpinAttack(),
                gc.getEspecialMoveOne(),
                gc.getEspecialMoveOTwo(),
                gc.getMenuAccess(),
                gc.getSelectItem(),
                gc.getUseItem()
        );

        return new UserControlsResponse(keyboard, gamepad);
    }

}
