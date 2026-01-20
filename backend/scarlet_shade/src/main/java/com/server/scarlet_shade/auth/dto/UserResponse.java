package com.server.scarlet_shade.auth.dto;

import java.math.BigDecimal;

import com.server.scarlet_shade.dto.SlotResponse;

public record UserResponse (
  
    BigDecimal soundtrack,

    BigDecimal soundEffect,

    SlotResponse slotOne,

    SlotResponse slotTwo,

    SlotResponse slotThree,

    SlotResponse slotFour
) {}