package com.server.scarlet_shade.dto.user;

import java.math.BigDecimal;

public record VolumeRequest(
        BigDecimal soundtrack,
        BigDecimal soundEffect
) {}
