package com.server.scarlet_shade.service;

import java.util.ArrayList;

import com.server.scarlet_shade.exception.player.InvalidSlotNumber;
import org.springframework.stereotype.Service;

import com.server.scarlet_shade.dto.player.player.PlayerResponse;
import com.server.scarlet_shade.dto.player.slot.SlotValues;
import com.server.scarlet_shade.dto.world.worldProgress.WorldProgressResponse;
import com.server.scarlet_shade.model.User;
import com.server.scarlet_shade.model.player.Player;
import com.server.scarlet_shade.model.player.Slot;
import com.server.scarlet_shade.model.world.WorldProgress;
import com.server.scarlet_shade.repository.UserRepository;
import com.server.scarlet_shade.repository.player.SlotRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SlotService {
 
    private final SlotRepository slotRepository;
    private final UserRepository userRepository;

    @Transactional
    public SlotValues createSlot(Long idIser, int numberSlot) {

        User user = userRepository.findById(idIser)
            .orElseThrow(() -> new RuntimeException("User not found"));

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
    public SlotValues getSlotByNumber(Long idUser, int numberSlot) {

        Slot slot = slotRepository.getSlot(idUser, numberSlot);

        String element;
        if (slot.getPlayer().getElement() == null) {
            element = null;
        }
        else {
            element = slot.getPlayer().getElement().toString();
        }

        PlayerResponse playerResponse = new PlayerResponse(
            slot.getPlayer().getDamage(), 
            slot.getPlayer().getSpeed(), 
            slot.getPlayer().getLife(), 
            slot.getPlayer().getMaxLife(), 
            slot.getPlayer().getMoney(),
            element,
            slot.getPlayer().getCurrentYokai());
        
        WorldProgressResponse worldProgressResponse = new 
            WorldProgressResponse(slot.getWorldProgress().getCurrentPhase().toString());
        
        SlotValues slotValues = new SlotValues(
            numberSlot, 
            slot.getGameCompleted(),
            playerResponse,
            worldProgressResponse);

        return slotValues;
    }

    @Transactional
    public ArrayList<Slot> getSlotsByUser(User user) {

        ArrayList<Slot> slots = (ArrayList<Slot>) slotRepository.getAllSlot(user.getId());
        return slots;
    }

    @Transactional
    public void deleteSlotByNumberByUser(Long idUser, int numberSlot){
        slotRepository.deleteSlotByNumberByUser(idUser, numberSlot);

        if(numberSlot > 4 || numberSlot <= 0){
            throw new InvalidSlotNumber();
        }
    }
}