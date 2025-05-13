package com.example.Games.game;

import com.example.Games.category.Category;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.math.BigDecimal;
import java.time.LocalDateTime;


@EntityListeners(AuditingEntityListener.class)

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Game {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "game_seq")
    @SequenceGenerator(name = "game_seq", sequenceName = "game_sequence", allocationSize = 5)
    private Long id;

    private String title;
    private String author;
    private BigDecimal price;
    private LocalDateTime releaseDate;
    private BigDecimal rating;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "category_id")
    private Category category;


    //@CreatedDate
    //@Column(updatable = false)
    private LocalDateTime createdAt;

    //@LastModifiedDate
    private LocalDateTime updatedAt;

    @PrePersist
    public void prePersist() {
        LocalDateTime now = LocalDateTime.now();
        createdAt = now;
        updatedAt = now;
    }

    @PreUpdate
    public void preUpdate() {
        updatedAt = LocalDateTime.now();
    }


    @Builder
    public Game(String title, String author, BigDecimal price, LocalDateTime releaseDate, BigDecimal rating, Category category) {
        this.title = title;
        this.author = author;
        this.price = price;
        this.releaseDate = releaseDate;
        this.rating = rating;
        this.category = category;
    }


    public static Game of(String title, String author, BigDecimal price, LocalDateTime releaseDate, BigDecimal rating, Category category){
        Game game = new Game();
        game.title = title;
        game.author = author;
        game.price = price;
        game.releaseDate = releaseDate;
        game.rating = rating;
        game.category = category;
        return game;
    }
}
