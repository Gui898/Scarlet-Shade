package com.server.scarlet_shade.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import com.server.scarlet_shade.dto.user.UserConfigurationResponse;
import com.server.scarlet_shade.dto.user.UserRequest;
import com.server.scarlet_shade.dto.user.VolumeRequest;
import com.server.scarlet_shade.model.User;
import com.server.scarlet_shade.security.token.CookieConfiguration;
import com.server.scarlet_shade.security.user.UserSecurity;
import com.server.scarlet_shade.service.UserService;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final CookieConfiguration cookieConfiguration;
 
    @PatchMapping("/volume")
    public ResponseEntity<Void> updateVolume(@RequestBody VolumeRequest request, @AuthenticationPrincipal UserSecurity userSecurity){
        User user = userSecurity.getUser();

        userService.updateVolume(request, user);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/update")
    public ResponseEntity<Void> updateUserConfigurations(@RequestBody @Valid UserRequest request, @AuthenticationPrincipal UserSecurity userSecurity, HttpServletResponse httpServletResponse) {
        User user = userSecurity.getUser();

        userService.updateUser(request, user);
        cookieConfiguration.refreshCookie(user, httpServletResponse);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/configurations")
    public ResponseEntity<UserConfigurationResponse> getUserConfigurations(@AuthenticationPrincipal UserSecurity userSecurity) {
        User user = userSecurity.getUser();

        UserConfigurationResponse response = userService.getUserConfigurations(user);
        
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Void> deleteUser(@AuthenticationPrincipal UserSecurity userSecurity){
        User user = userSecurity.getUser();
        userService.deleteUser(user);

        return ResponseEntity.noContent().build();
    }
}