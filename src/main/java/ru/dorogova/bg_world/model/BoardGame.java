package ru.dorogova.bg_world.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
    @Column(name = "title", nullable = false)
    private String title;

    /**
     * Жанр игры
     */
    @Column(name = "genre", nullable = false)
    private String genre;

    /**
     * Дата появления игры в коллекции
     */
    @Column(name = "added_to_collection")
    private String gettingDate;

    /**
     * Одна настольная игра может иметь несколько партий
     */
    @OneToMany(mappedBy = "boardGame", cascade = {CascadeType.MERGE, CascadeType.PERSIST},
            fetch = FetchType.EAGER, orphanRemoval = true)
    @JsonManagedReference
    private List<Session> sessions;

    /**
     * связь - несколько настольных игр у одного юзера
     */
    //@ManyToOne
    //@JoinColumn(name = "user_id", nullable = false)
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST}, fetch = FetchType.EAGER)
    @JoinColumn(name = "user_name")
    @JsonBackReference
    private User user;

    /**
     * Установка обратной связи в партией
     * чтобы иметь возможность добавить партию к настольной игре
     */
    public void addSession(Session session) {
        session.setBoardGame(this); // Установка обратной связи
        this.sessions.add(session);
    }

}
