package com.server.scarlet_shade.dto.user;

import java.math.BigDecimal;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;

public record VolumeRequest(

        @DecimalMin(value = "0.0", inclusive = true, message = "Soundtrack must be between 0 and 1")
        @DecimalMax(value = "1.0", inclusive = true, message = "Soundtrack must be between 0 and 1")
        BigDecimal soundtrack,

        @DecimalMin(value = "0.0", inclusive = true, message = "Sound Effects must be between 0 and 1")
        @DecimalMax(value = "1.0", inclusive = true, message = "Sound Effects must be between 0 and 1")
        BigDecimal soundEffect
) {}