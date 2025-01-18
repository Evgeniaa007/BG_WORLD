package ru.dorogova.bg_world.model;

import jakarta.persistence.*;
import lombok.Data;

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
    @Column(name = "title")
    private String title;

    /**
     * Жанр игры
     */
    @Column(name = "genre")
    private String genre;

    /**
     * Дата появления игры в коллекции
     */
    @Column(name = "added_to_collection (DD-MM-YYYY)")
    private String gettingDate;

    /**
     * Одна настольная игра может иметь несколько партий
     */
    @OneToMany(mappedBy = "boardGame", cascade = {CascadeType.MERGE, CascadeType.PERSIST},
            fetch = FetchType.EAGER, orphanRemoval = true)
    private List<Session> sessions;

    /**
     * связь - несколько настольных игр у одного юзера
     */
    //@ManyToOne
    //@JoinColumn(name = "user_id", nullable = false)
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST}, fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    public void addSession(Session session) {
        session.setBoardGame(this); // Установка обратной связи
        this.sessions.add(session);
    }

}
