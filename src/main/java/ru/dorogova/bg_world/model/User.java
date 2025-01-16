package ru.dorogova.bg_world.model;

import jakarta.persistence.*;
import lombok.Data;

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
    @Column(nullable = false, unique = true)
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
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<BoardGame> boardGameList;



}
