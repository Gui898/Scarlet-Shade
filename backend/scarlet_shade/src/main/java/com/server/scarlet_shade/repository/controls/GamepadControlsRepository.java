package com.server.scarlet_shade.repository.controls;

import org.springframework.data.jpa.repository.JpaRepository;

import com.server.scarlet_shade.model.controls.GamepadControls;

public interface GamepadControlsRepository extends JpaRepository<GamepadControls, Long> {
    
}