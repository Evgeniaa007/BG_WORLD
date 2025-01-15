package ru.dorogova.bg_world.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

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



}
