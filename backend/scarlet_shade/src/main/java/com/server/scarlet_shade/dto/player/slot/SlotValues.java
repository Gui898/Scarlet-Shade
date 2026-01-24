package com.server.scarlet_shade.dto.player.slot;

import com.server.scarlet_shade.dto.player.player.PlayerResponse;
import com.server.scarlet_shade.dto.world.worldProgress.WorldProgressResponse;

public record SlotValues (

    int numberSlot,
    boolean gameComplete,
    PlayerResponse player,
    WorldProgressResponse worldProgress
) {}