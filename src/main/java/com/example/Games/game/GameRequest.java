package com.example.Games.game;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record GameRequest(
        @NotBlank(message = "Title is null")
        String title,

        @NotBlank(message = "Author is null")
        String author,

        @NotNull(message = "No Release Date")
        LocalDateTime releaseDate,

        @NotNull(message = "price is required")
        @Min(value = 0, message = "Price should be more than 0")
        BigDecimal price,

        @NotNull(message = "Category ID is required")
        Long categoryId
) {
}
