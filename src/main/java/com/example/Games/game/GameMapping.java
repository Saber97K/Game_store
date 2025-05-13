package com.example.Games.game;

import com.example.Games.category.Category;
import com.example.Games.category.CategoryMapping;

import java.math.BigDecimal;

public class GameMapping {

    public static GameResponse toDto(Game game) {
        return new GameResponse(
                game.getId(),
                game.getTitle(),
                game.getAuthor(),
                game.getPrice(),
                game.getRating(),
                game.getReleaseDate(),
                CategoryMapping.toDto(game.getCategory())
        );
    }

    public static Game toEntity(GameRequest gameRequest, Category category){
        return Game.of(
                gameRequest.title(),
                gameRequest.author(),
                gameRequest.price(),
                gameRequest.releaseDate(),
                BigDecimal.valueOf(0),
                category
        );
    }
}
