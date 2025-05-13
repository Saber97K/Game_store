package com.example.Games.game;

import com.example.Games.category.CategoryResponse;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public record GameResponse(
        Long id,
        String title,
        String author,
        BigDecimal price,
        BigDecimal rating,
        LocalDateTime releaseDate,
        CategoryResponse category) {}
