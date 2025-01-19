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
    @Column(name = "sessionDate")
    private LocalDate sessionDate;
    /**
     * Кол-во игроков. участвующих в партии
     */
    @Column(name = "playersAmount")
    private int playersAmount;
    /**
     * Победитель
     */
    @Column(name = "winner")
    private String winner;
    /**
     * Впечатления (комментарии) к партии
     */
    @Column(name = "impressions")
    private String impressions;

    @ManyToOne
    @JoinColumn(name = "boardGame_id")
    @JsonBackReference
    private BoardGame boardGame;


    /**
     * Владелец настольной игры
     */
    /*
    @ManyToOne
    @JoinColumn(name = "users_id", nullable = false)
    private User user;

*/


}
