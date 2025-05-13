package com.example.Games.game;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/games")
@RequiredArgsConstructor
public class GameController {

    private final GameService gameService;

    @PostMapping
    public ResponseEntity<GameResponse> create(@RequestBody @Valid GameRequest request) {
        return ResponseEntity.ok(gameService.createGame(request));
    }

    @GetMapping
    public ResponseEntity<List<GameResponse>> getAll() {
        return ResponseEntity.ok(gameService.getAllGames());
    }

    @GetMapping("/{title}")
    public ResponseEntity<GameResponse> getByTitle(@PathVariable String title) {
        return ResponseEntity.ok(gameService.getGameById(title));
    }

    @PutMapping("/{id}")
    public ResponseEntity<GameResponse> update(@PathVariable Long id, @RequestBody @Valid GameRequest request) {
        return ResponseEntity.ok(gameService.updateGame(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        gameService.deleteGame(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search/author")
    public ResponseEntity<List<GameResponse>> searchByAuthor(@RequestParam String author) {
        return ResponseEntity.ok(gameService.searchGamesByAuthorNative(author));
    }

    @GetMapping("/search/title")
    public ResponseEntity<List<GameResponse>> searchByTitle(@RequestParam String title) {
        return ResponseEntity.ok(gameService.searchByTitle(title));
    }

    @GetMapping("/filter/price")
    public ResponseEntity<List<GameResponse>> filterByPriceRange(
            @RequestParam BigDecimal min,
            @RequestParam BigDecimal max
    ) {
        return ResponseEntity.ok(gameService.getGamesInPriceRange(min, max));
    }

    @GetMapping("/sorted")
    public ResponseEntity<List<GameResponse>> getSorted() {
        return ResponseEntity.ok(gameService.getGamesSortedByPrice());
    }
}
