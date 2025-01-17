package ru.dorogova.bg_world.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "sessions")
public class Session {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    /**
     * Название настольной игры
     */
    @Column(nullable = false)
    private String bgName;
    /**
     * Владелец настольной игры
     */
    @ManyToOne
    @JoinColumn(name = "owner_id", nullable = false)
    private User owner;

    /**
     * Дата проведения партии
     */
    @Column(nullable = false)
    private LocalDate sessionDate;
    /**
     * Кол-во игроков. участвующих в партии
     */
    @Column(nullable = false)
    private int playersAmount;
    /**
     * Победитель
     */
    @Column(nullable = false)
    private String winner;
    /**
     * Впечатления (комментарии) к партии
     */
    @Column(nullable = false)
    private String impressions;



}
