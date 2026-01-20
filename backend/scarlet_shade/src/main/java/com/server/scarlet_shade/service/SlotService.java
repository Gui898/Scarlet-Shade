package com.server.scarlet_shade.service;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.server.scarlet_shade.repository.player.SlotRepository;

import jakarta.transaction.Transactional;

import com.server.scarlet_shade.model.User;
import com.server.scarlet_shade.model.player.Slot;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SlotService {
 
    private final SlotRepository slotRepository;

    @Transactional
    public ArrayList<Slot> getSlotsByUser(User user) {

        ArrayList<Slot> slots = (ArrayList<Slot>) slotRepository.getAllSlot(user.getId());
        return slots;
    }
}