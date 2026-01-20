package com.server.scarlet_shade.controller;

import com.server.scarlet_shade.dto.user.VolumeRequest;
import com.server.scarlet_shade.model.User;
import com.server.scarlet_shade.security.user.UserSecurity;
import com.server.scarlet_shade.service.UserService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    UserService userService;
 
    @PatchMapping("/volume")
    public ResponseEntity<Void> updateVolume(@RequestBody VolumeRequest request, @AuthenticationPrincipal UserSecurity userSecurity){
        User user = userSecurity.getUser();

        userService.updateVolume(request, user);
        return ResponseEntity.noContent().build();
    }
}