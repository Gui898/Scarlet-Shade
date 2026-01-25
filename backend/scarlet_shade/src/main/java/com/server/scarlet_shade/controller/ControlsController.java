package com.server.scarlet_shade.controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.server.scarlet_shade.dto.controls.ControlsRequestResponse;
import com.server.scarlet_shade.model.User;
import com.server.scarlet_shade.security.user.UserSecurity;
import com.server.scarlet_shade.service.ControlsService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/control")
@RequiredArgsConstructor
public class ControlsController {
    
    private final ControlsService controlsService;

    @PatchMapping("/update")
    public ResponseEntity<Void> updateControls(@RequestBody @Valid ControlsRequestResponse request, @AuthenticationPrincipal UserSecurity userSecurity){
        User user = userSecurity.getUser();
        controlsService.updateControls(request, user);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/buttons")
    public ResponseEntity<ControlsRequestResponse> getControls(@AuthenticationPrincipal UserSecurity userSecurity){
        User user = userSecurity.getUser();
        ControlsRequestResponse controls = controlsService.getControlsByUser(user);

        return ResponseEntity.status(HttpStatus.OK).body(controls);
    }
}