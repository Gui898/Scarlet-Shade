package com.server.scarlet_shade.dto.player.player;

public record PlayerResponse (

    int damage,
    int speed,
    int life,
    int maxLife,
    int money,
    String element,
    String currentYokai
) {}