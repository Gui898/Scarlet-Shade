package com.server.scarlet_shade.auth.dto;

import java.math.BigDecimal;

import com.server.scarlet_shade.dto.player.slot.SlotResponse;

public record UserResponse (
  
    BigDecimal soundtrack,

    BigDecimal soundEffect,

    SlotResponse slotOne,

    SlotResponse slotTwo,

    SlotResponse slotThree,

    SlotResponse slotFour
) {}