package com.example.Games.game.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record CreateRequest(
        @NotBlank(message = "Title is null")
        String title,

        @NotNull(message = "price is required")
        @Min(value = 0, message = "Price should be more than 0")
        BigDecimal price,

        @NotNull(message = "Category ID is required")
        Long categoryId
) {
}
