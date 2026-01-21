package com.server.scarlet_shade.service;

import com.server.scarlet_shade.model.User;
import com.server.scarlet_shade.model.controls.Controls;
import com.server.scarlet_shade.repository.controls.GamepadControlsRepository;
import com.server.scarlet_shade.repository.controls.KeyboardControlsRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class ControlsService {

    private GamepadControlsRepository gamepadControlsRepository;
    private KeyboardControlsRepository keyboardControlsRepository;

    @Transactional
    public ArrayList<Controls> getControlsByUser(User user){
        ArrayList<Controls> controls = new ArrayList<>();
        controls.add(keyboardControlsRepository.getKeyboardControl(user.getId()));
        controls.add(gamepadControlsRepository.getGamepadControl(user.getId()));

        return controls;
    }

}
