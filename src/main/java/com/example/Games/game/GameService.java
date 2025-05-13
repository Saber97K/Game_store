package com.example.Games.game;

import com.example.Games.category.Category;
import com.example.Games.category.CategoryRepository;
import com.example.Games.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GameService {

    private final GameRepository gameRepository;
    private final CategoryRepository categoryRepository;
    private final GameMapStruct gameMapStruct;


    // ✅ Create
    public GameResponse createGame(GameRequest request) {
        Category category = categoryRepository.findById(request.categoryId())
                .orElseThrow(() -> new ResourceNotFoundException("Category not found"));

        Game game = gameMapStruct.toEntity(request,category);

        return gameMapStruct.toDto(gameRepository.save(game));
    }

    // ✅ Read all
    public List<GameResponse> getAllGames() {
        return gameRepository.findAll()
                .stream()
                .map(gameMapStruct::toDto)
                .toList();
    }

    // ✅ Read by ID
    public GameResponse getGameById(String title) {
        Game game = gameRepository.findByTitle(title)
                .orElseThrow(() -> new ResourceNotFoundException("Game not found"));
        return gameMapStruct.toDto(game);
    }

    // ✅ Update
    public GameResponse updateGame(Long id, GameRequest request) {
        Game game = gameRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Game not found"));


        Category category = categoryRepository.findById(request.categoryId())
                .orElseThrow(() -> new ResourceNotFoundException("Category not found"));

        game.setPrice(request.price());
        game.setCategory(category);
        /*
         Optional: title and author updates
         game.updateTitle(request.title());
         game.updateAuthor(request.author());
        */

        return gameMapStruct.toDto(gameRepository.save(game));
    }
    // ✅ Delete
    public void deleteGame(Long id) {
        if (!gameRepository.existsById(id)) {
            throw new ResourceNotFoundException("Game not found");
        }
        gameRepository.deleteById(id);
    }

    // ✅ findByTitle (Spring method naming)
    public List<GameResponse> searchByTitle(String title) {
        return gameRepository.findByTitle(title)
                .stream()
                .map(gameMapStruct::toDto)
                .toList();
    }

    // ✅ findGamesInPriceRange (custom JPQL)
    public List<GameResponse> getGamesInPriceRange(BigDecimal minPrice, BigDecimal maxPrice) {
        return gameRepository.findGamesInPriceRange(minPrice, maxPrice)
                .stream()
                .map(gameMapStruct::toDto)
                .toList();
    }

    // ✅ findByCategoryId + Pageable
    public Page<GameResponse> getGamesByCategoryPaged(Long categoryId, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return gameRepository.findByCategoryId(categoryId, pageable)
                .map(gameMapStruct::toDto);
    }

    // ✅ Sorting by price ascending
    public List<GameResponse> getGamesSortedByPrice() {
        return gameRepository.findAllByOrderByPriceAsc()
                .stream()
                .map(gameMapStruct::toDto)
                .toList();
    }

    // ✅ Native query (search by author)
    public List<GameResponse> searchGamesByAuthorNative(String author) {
        return gameRepository.searchByAuthorNative(author)
                .stream()
                .map(gameMapStruct::toDto)
                .toList();
    }

    // ✅ Combined: JPQL + pagination + sorting
    public Page<GameResponse> searchGamesByTitlePaged(String keyword, int page, int size, String sortBy, boolean asc) {
        Sort sort = asc ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(page, size, sort);
        return gameRepository.searchByTitle(keyword, pageable)
                .map(gameMapStruct::toDto);
    }

}
