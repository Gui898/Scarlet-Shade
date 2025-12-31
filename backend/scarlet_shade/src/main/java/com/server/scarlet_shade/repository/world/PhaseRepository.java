package com.server.scarlet_shade.repository.world;

import org.springframework.data.jpa.repository.JpaRepository;

import com.server.scarlet_shade.model.world.Phase;

public interface PhaseRepository extends JpaRepository<Phase, Long> {
    
}