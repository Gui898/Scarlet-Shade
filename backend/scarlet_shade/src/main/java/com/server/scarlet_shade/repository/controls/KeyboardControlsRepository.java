package com.server.scarlet_shade.repository.controls;

import com.server.scarlet_shade.model.controls.Controls;
import org.springframework.data.jpa.repository.JpaRepository;

import com.server.scarlet_shade.model.controls.KeyboardControls;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface KeyboardControlsRepository extends JpaRepository<KeyboardControls, Long> {
    @Query(value = "SELECT * FROM keyboard_control WHERE id_user = :idUser", nativeQuery = true)
    public Controls getKeyboardControl(@Param("idUser")long idUser);
}