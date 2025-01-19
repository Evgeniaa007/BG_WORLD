package ru.dorogova.bg_world.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "users")
public class User {

    /**
     * идентификатор пользователя
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    /**
     * имя пользователя
     */
    @Column(name = "name", nullable = false, unique = true)
    private String name;
    /**
     * пароль
     */
    @Column(nullable = false)
    private String password;
    /**
     * определяет связь с настольными играми. т.е.
     * один пользователь может иметь несколько игр
     */
    //@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @OneToMany(mappedBy = "user", cascade = {CascadeType.MERGE, CascadeType.PERSIST},
            fetch = FetchType.EAGER, orphanRemoval = true)
    @JsonManagedReference
    private List<BoardGame> boardGames;
/*
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Session> sessions;

*/
    public void addBoardGame(BoardGame boardGame) {
        boardGame.setUser(this); // Установка обратной связи
        this.boardGames.add(boardGame);
    }
}
