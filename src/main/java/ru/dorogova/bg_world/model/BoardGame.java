package ru.dorogova.bg_world.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@Table(name = "boardgames")
public class BoardGame {
    /**
     * Идентификатор игры
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Название игры
     */
    @Column(name = "title", nullable = false)
    private String title;

    /**
     * Жанр игры
     */
    @Column(name = "category", nullable = false)
    private String category;

    /**
     * Дата появления игры в коллекции
     */
    @Column(name = "added_to_collection")
    private LocalDate gettingDate = LocalDate.now();
    /**
     * связь - несколько настольных игр у одного юзера
     */
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    /**
     * Одна настольная игра может иметь несколько партий
     */
    @OneToMany(mappedBy = "boardgames", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Session> sessions;

}
