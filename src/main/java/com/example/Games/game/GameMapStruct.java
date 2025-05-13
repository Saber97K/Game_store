package com.example.Games.game;
import com.example.Games.common.CommonMapStruct;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import com.example.Games.category.Category;
import java.util.List;

@Mapper(componentModel = "spring", uses = { CommonMapStruct.class })
public interface GameMapStruct {

    //Map from DTO to Entity (no id, rating, or releaseDate in request)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "rating", expression = "java(new java.math.BigDecimal(\"0\"))")
    @Mapping(target = "category", source = "category")
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    Game toEntity(GameRequest request, Category category);

    //Map from Entity to Response
    GameResponse toDto(Game game);

    List<GameResponse> toDtoList(List<Game> games);
}
