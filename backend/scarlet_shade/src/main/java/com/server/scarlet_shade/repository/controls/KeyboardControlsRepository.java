package com.server.scarlet_shade.repository.controls;

import org.springframework.data.jpa.repository.JpaRepository;

import com.server.scarlet_shade.model.controls.KeyboardControls;

public interface KeyboardControlsRepository extends JpaRepository<KeyboardControls, Long> {
    
}