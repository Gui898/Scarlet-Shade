package com.server.scarlet_shade.repository.controls;

import org.springframework.data.jpa.repository.JpaRepository;

import com.server.scarlet_shade.model.controls.GamepadControls;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface GamepadControlsRepository extends JpaRepository<GamepadControls, Long> {
    @Query(value = "SELECT * FROM gamepad_control WHERE id_user = :idUser", nativeQuery = true)
    public GamepadControls getGamepadControl(@Param("idUser")long idUser);
}