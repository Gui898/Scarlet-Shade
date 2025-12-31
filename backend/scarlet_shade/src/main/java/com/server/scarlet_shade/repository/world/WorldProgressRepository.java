package com.server.scarlet_shade.repository.world;

import org.springframework.data.jpa.repository.JpaRepository;

import com.server.scarlet_shade.model.world.WorldProgress;

public interface WorldProgressRepository extends JpaRepository<WorldProgress, Long> {
    
}