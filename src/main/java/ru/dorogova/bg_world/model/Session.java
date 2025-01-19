package ru.dorogova.bg_world.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
    @Column(name = "BoardGameName")
    private String bgName;

    /**
     * Дата проведения партии
     */
    @Column(name = "sessionDate", nullable = false)
    private String sessionDate;
    /**
     * Кол-во игроков. участвующих в партии
     */
    @Column(name = "playersAmount", nullable = false)
    private int playersAmount;
    /**
     * Победитель
     */
    @Column(name = "winner", nullable = false)
    private String winner;
    /**
     * Впечатления (комментарии) к партии
     */
    @Column(name = "impressions")
    private String impressions;

    /**
     * Связь, определябщая, что у одной настольной игры
     * может быть несколько сессий
     */
    @ManyToOne
    @JoinColumn(name = "boardGame_id")
    @JsonBackReference
    private BoardGame boardGame;

}
