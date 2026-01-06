package com.server.scarlet_shade.service;

import org.springframework.stereotype.Service;

import com.server.scarlet_shade.exception.user.UserConflictException;
import com.server.scarlet_shade.model.User;
import com.server.scarlet_shade.model.controls.GamepadControls;
import com.server.scarlet_shade.model.controls.KeyboardControls;
import com.server.scarlet_shade.repository.UserRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
 
    private final UserRepository userRepository;

    @Transactional
    public void createUser(String username, String email, String password) {

        if (userRepository.existsByUsername(username) || userRepository.existsByEmail(email)) {
            throw new UserConflictException();
        }

        User user = new User(username, email, password);

        KeyboardControls keyboardControls = new KeyboardControls();
        keyboardControls.setUser(user);

        GamepadControls gamepadControls = new GamepadControls();
        gamepadControls.setUser(user);

        user.setKeyboardControls(keyboardControls);
        user.setGamepadControls(gamepadControls);

        userRepository.save(user);
    }
}