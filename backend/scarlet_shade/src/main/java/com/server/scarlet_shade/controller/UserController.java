package com.server.scarlet_shade.controller;

import com.server.scarlet_shade.dto.user.ControlsDTO;
import com.server.scarlet_shade.dto.user.UserControlsResponse;
import com.server.scarlet_shade.service.ControlsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import com.server.scarlet_shade.dto.user.VolumeRequest;
import com.server.scarlet_shade.model.User;
import com.server.scarlet_shade.security.user.UserSecurity;
import com.server.scarlet_shade.service.UserService;

import lombok.RequiredArgsConstructor;

import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final ControlsService controlsService;
 
    @PatchMapping("/volume")
    public ResponseEntity<Void> updateVolume(@RequestBody VolumeRequest request, @AuthenticationPrincipal UserSecurity userSecurity){
        User user = userSecurity.getUser();

        userService.updateVolume(request, user);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/controls")
    public ResponseEntity<UserControlsResponse> getControls(@AuthenticationPrincipal UserSecurity userSecurity){
        User user = userSecurity.getUser();
        UserControlsResponse controls = controlsService.getControlsByUser(user);

        return ResponseEntity.status(HttpStatus.OK).body(controls);
    }
}