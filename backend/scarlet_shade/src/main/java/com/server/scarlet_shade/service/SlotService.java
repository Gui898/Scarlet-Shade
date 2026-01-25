package com.server.scarlet_shade.service;

import java.util.ArrayList;

import com.server.scarlet_shade.exception.player.slot.InvalidSlotNumberException;
import com.server.scarlet_shade.exception.player.slot.SlotNotFoundException;
import com.server.scarlet_shade.exception.user.UserNotFoundException;
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
    public SlotValues createSlot(Long idUser, int numberSlot) {

        User user = userRepository.findById(idUser)
            .orElseThrow(UserNotFoundException::new);

        verifyNumberSlot(numberSlot);

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

        return new SlotValues(
            numberSlot,
            false,
            playerResponse,
            worldProgressResponse);
    }

    @Transactional
    public SlotValues getSlotByNumber(Long idUser, int numberSlot) {

        verifyNumberSlot(numberSlot);

        Slot slot = slotRepository.getSlot(idUser, numberSlot);

        if(slot == null){
            throw new SlotNotFoundException();
        }

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

        return new SlotValues(
            numberSlot,
            slot.getGameCompleted(),
            playerResponse,
            worldProgressResponse);
    }

    @Transactional
    public ArrayList<Slot> getSlotsByUser(User user) {

        if(user == null){
            throw new UserNotFoundException();
        }

        ArrayList<Slot> slots = (ArrayList<Slot>) slotRepository.getAllSlot(user.getId());

        if(slots.isEmpty()){
            throw new SlotNotFoundException();
        }

        return slots;
    }

    @Transactional
    public void deleteSlotByNumberByUser(Long idUser, int numberSlot){
        verifyNumberSlot(numberSlot);

        Slot slot = slotRepository.getSlot(idUser, numberSlot);
        slotRepository.delete(slot);
    }

    public static void verifyNumberSlot(int numberSlot){
        if(numberSlot > 4 || numberSlot <= 0){
            throw new InvalidSlotNumberException();
        }
    }
}