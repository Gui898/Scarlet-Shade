package com.server.scarlet_shade.repository.controls;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.server.scarlet_shade.model.controls.KeyboardControls;

public interface KeyboardControlsRepository extends JpaRepository<KeyboardControls, Long> {
    
    @Query(value = "SELECT * FROM keyboard_control WHERE id_user = :idUser", nativeQuery = true)
    public KeyboardControls getKeyboardControl(@Param("idUser")long idUser);
}