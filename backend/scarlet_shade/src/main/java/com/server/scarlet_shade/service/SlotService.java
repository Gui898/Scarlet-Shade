package com.server.scarlet_shade.service;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.server.scarlet_shade.dto.player.player.PlayerResponse;
import com.server.scarlet_shade.dto.player.slot.SlotValues;
import com.server.scarlet_shade.dto.world.worldProgress.WorldProgressResponse;
import com.server.scarlet_shade.model.User;
import com.server.scarlet_shade.model.player.Player;
import com.server.scarlet_shade.model.player.Slot;
import com.server.scarlet_shade.model.world.WorldProgress;
import com.server.scarlet_shade.repository.player.SlotRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SlotService {
 
    private final SlotRepository slotRepository;

    @Transactional
    public SlotValues createSlot(User user, int numberSlot) {

        Slot slot = new Slot(numberSlot, false);
        slot.setUser(user);
        user.getSlots().add(slot);

        WorldProgress worldProgress = new WorldProgress();
        Player player = new Player();

        slot.setWorldProgress(worldProgress);
        slot.setPlayer(player);

        worldProgress.setSlot(slot);
        player.setSlot(slot);

        slotRepository.save(slot);

        PlayerResponse playerResponse = new PlayerResponse(
            player.getDamage(), 
            player.getSpeed(), 
            player.getLife(), 
            player.getMaxLife(), 
            player.getMoney(),
            null,
            null);
        
        WorldProgressResponse worldProgressResponse = new 
            WorldProgressResponse(worldProgress.getCurrentPhase().toString());
        
        SlotValues slotValues = new SlotValues(
            numberSlot, 
            false, 
            playerResponse,
            worldProgressResponse);

        return slotValues;
    }

    @Transactional
    public SlotValues getSlotByNumber(User user, int numberSlot) {

        Slot slot = slotRepository.getSlot(user.getId(), numberSlot);

        PlayerResponse playerResponse = new PlayerResponse(
            slot.getPlayer().getDamage(), 
            slot.getPlayer().getSpeed(), 
            slot.getPlayer().getLife(), 
            slot.getPlayer().getMaxLife(), 
            slot.getPlayer().getMoney(),
            slot.getPlayer().getElement().toString(),
            slot.getPlayer().getCurrentYokai());
        
        WorldProgressResponse worldProgressResponse = new 
            WorldProgressResponse(slot.getWorldProgress().getCurrentPhase().toString());
        
        SlotValues slotValues = new SlotValues(
            numberSlot, 
            false, 
            playerResponse,
            worldProgressResponse);

        return slotValues;
    }

    @Transactional
    public ArrayList<Slot> getSlotsByUser(User user) {

        ArrayList<Slot> slots = (ArrayList<Slot>) slotRepository.getAllSlot(user.getId());
        return slots;
    }
}