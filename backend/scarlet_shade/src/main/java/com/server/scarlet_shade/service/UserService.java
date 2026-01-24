package com.server.scarlet_shade.service;

import java.util.ArrayList;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.server.scarlet_shade.auth.dto.UserResponse;
import com.server.scarlet_shade.dto.player.slot.SlotResponse;
import com.server.scarlet_shade.dto.user.UserConfigurationResponse;
import com.server.scarlet_shade.dto.user.UserRequest;
import com.server.scarlet_shade.dto.user.VolumeRequest;
import com.server.scarlet_shade.exception.user.UserConflictException;
import com.server.scarlet_shade.model.User;
import com.server.scarlet_shade.model.controls.GamepadControls;
import com.server.scarlet_shade.model.controls.KeyboardControls;
import com.server.scarlet_shade.model.player.Slot;
import com.server.scarlet_shade.repository.UserRepository;
import com.server.scarlet_shade.security.token.CookieConfiguration;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
 
    private final UserRepository userRepository;
    private final SlotService slotService;
    private final PasswordEncoder passwordEncoder;
    private final CookieConfiguration cookieConfiguration;

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

    public UserConfigurationResponse getUserConfigurations(User user) {

        UserConfigurationResponse response = new UserConfigurationResponse(
            user.getUsername(), 
            user.getEmail()
        );

        return response;
    }

    @Transactional
    public void updateUser(UserRequest request, User user) {
        
        user.setUsername(request.username());
        user.setEmail(request.email());

        if (request.password() != null && !request.password().isBlank()) {
            user.setPassword(passwordEncoder.encode(request.password()));
        }

        userRepository.save(user);
    }

    @Transactional
    public void updateVolume(VolumeRequest request, User user){
        user.setSoundtrack(request.soundtrack());
        user.setSoundEffects(request.soundEffect());

        userRepository.save(user);
    }

    @Transactional
    public void deleteUser(User user, HttpServletResponse httpServletResponse){
        userRepository.delete(user);
        cookieConfiguration.authDeleteCookie(httpServletResponse);
    }
}