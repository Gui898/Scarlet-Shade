package com.server.scarlet_shade.repository.player;

import org.springframework.data.jpa.repository.JpaRepository;

import com.server.scarlet_shade.model.player.Player;

public interface PlayerRepository extends JpaRepository<Player, Long> {
    
}
