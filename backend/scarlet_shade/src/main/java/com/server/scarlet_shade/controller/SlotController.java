package com.server.scarlet_shade.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.server.scarlet_shade.dto.player.slot.SlotValues;
import com.server.scarlet_shade.model.User;
import com.server.scarlet_shade.security.user.UserSecurity;
import com.server.scarlet_shade.service.SlotService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/slot")
@RequiredArgsConstructor
public class SlotController {
    
    private final SlotService slotService;

    @PostMapping("/create")
    public ResponseEntity<SlotValues> createSlot(@RequestParam("number") int numberSlot, @AuthenticationPrincipal UserSecurity userSecurity) {
        User user = userSecurity.getUser();

        SlotValues slotValues = slotService.createSlot(user.getId(), numberSlot);

        return ResponseEntity.status(HttpStatus.CREATED).body(slotValues);
    }

    @GetMapping("/get")
    public ResponseEntity<SlotValues> getSlot(@RequestParam("number") int numberSlot, @AuthenticationPrincipal UserSecurity userSecurity) {
        User user = userSecurity.getUser();
        
        SlotValues slotValues = slotService.getSlotByNumber(user.getId(), numberSlot);

        return ResponseEntity.status(HttpStatus.OK).body(slotValues);
    }
}