package ru.dorogova.bg_world.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.dorogova.bg_world.model.BoardGame;

public interface BoardGameRepository extends JpaRepository<BoardGame, Long> {
}
