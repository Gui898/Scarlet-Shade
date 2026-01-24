package com.server.scarlet_shade.repository.player;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.server.scarlet_shade.model.player.Slot;

public interface SlotRepository extends JpaRepository<Slot, Long> {
    
    @Query(value = "SELECT * FROM slot WHERE id_user = :idUser", nativeQuery = true)
    public List<Slot> getAllSlot(@Param("idUser") long idUser);

    @Query(value = "SELECT * FROM slot WHERE id_user = :idUser AND number_slot = :numberSlot", nativeQuery = true)
    public Slot getSlot(@Param("idUser") long idUser, @Param("numberSlot") int numberSlot);

    @Query(value = "DELETE FROM slot WHERE id_user = :idUser AND number_slot = :numberSlot", nativeQuery = true)
    public void deleteSlotByNumberByUser(@Param("idUser") long idUser, @Param("numberSlot") int numberSlot);
}