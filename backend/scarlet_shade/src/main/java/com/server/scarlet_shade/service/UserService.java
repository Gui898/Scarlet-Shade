package com.server.scarlet_shade.service;

import java.util.ArrayList;

import com.server.scarlet_shade.dto.user.UserRequestResponse;
import com.server.scarlet_shade.dto.user.VolumeRequest;
import org.springframework.stereotype.Service;

import com.server.scarlet_shade.auth.dto.UserResponse;
import com.server.scarlet_shade.dto.SlotResponse;
import com.server.scarlet_shade.exception.user.UserConflictException;
import com.server.scarlet_shade.model.User;
import com.server.scarlet_shade.model.controls.GamepadControls;
import com.server.scarlet_shade.model.controls.KeyboardControls;
import com.server.scarlet_shade.model.player.Slot;
import com.server.scarlet_shade.repository.UserRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
 
    private final UserRepository userRepository;
    private final SlotService slotService;

    @Transactional
    public User createUser(String username, String email, String password) {

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

        return user;
    }

    public UserResponse getUserResponse(User user) {
        
        ArrayList<Slot> slots = slotService.getSlotsByUser(user);

        SlotResponse slotOne = null;
        SlotResponse slotTwo = null;
        SlotResponse slotThree = null;
        SlotResponse slotFour = null;

        for (Slot slot : slots) {

            switch (slot.getNumberSlot()) {
                case 1 -> slotOne = new SlotResponse(slot.getNumberSlot(), slot.getGameCompleted());
                case 2 -> slotTwo = new SlotResponse(slot.getNumberSlot(), slot.getGameCompleted());
                case 3 -> slotThree = new SlotResponse(slot.getNumberSlot(), slot.getGameCompleted());
                case 4 -> slotFour = new SlotResponse(slot.getNumberSlot(), slot.getGameCompleted());
            }
        }

        UserResponse userResponse = new UserResponse(
            user.getSoundtrack(),
            user.getSoundEffects(),
            slotOne, 
            slotTwo, 
            slotThree, 
            slotFour);

        return userResponse;
    }

    public UserRequestResponse getUserConfigurations(User user) {

        UserRequestResponse response = new UserRequestResponse(
            user.getUsername(), 
            user.getEmail(), 
            user.getPassword()
        );

        return response;
    }

    public void updateUser(UserRequestResponse request, User user) {
        user.setUsername(request.username());
        user.setEmail(request.email());
        user.setPassword(request.password());

        userRepository.save(user);
    }

    public void updateVolume(VolumeRequest request, User user){
        user.setSoundtrack(request.soundtrack());
        user.setSoundEffects(request.soundEffect());

        userRepository.save(user);
    }
}