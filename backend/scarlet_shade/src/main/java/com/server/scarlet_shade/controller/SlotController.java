package com.server.scarlet_shade.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

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

        SlotValues slotValues = slotService.createSlot(user, numberSlot);

        return ResponseEntity.status(HttpStatus.CREATED).body(slotValues);
    }

    @GetMapping("/get")
    public ResponseEntity<SlotValues> getSlot(@RequestParam("number") int numberSlot, @AuthenticationPrincipal UserSecurity userSecurity) {
        User user = userSecurity.getUser();
        
        SlotValues slotValues = slotService.getSlotByNumber(user, numberSlot);

        return ResponseEntity.status(HttpStatus.OK).body(slotValues);
    }

    @DeleteMapping("/delete/{numSlot}")
    public ResponseEntity<Void> deleteSlotByNumberByUser(@RequestParam("id") int numSlot, UserSecurity userSecurity){
        User user = userSecurity.getUser();
        slotService.deleteSlotByNumberByUser(user, numSlot);

        return ResponseEntity.noContent().build();
    }
}