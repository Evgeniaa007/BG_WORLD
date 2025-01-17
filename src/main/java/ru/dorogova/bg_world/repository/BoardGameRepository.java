package ru.dorogova.bg_world.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.dorogova.bg_world.model.BoardGame;

import java.util.List;

public interface BoardGameRepository extends JpaRepository<BoardGame, Long> {
    /**
     * Метод, позволяющий вывести список игр, которые
     * принадлежат определенному пользователю в соответствии с его ID
     */
    List<BoardGame> getByOwner(Long ownerId);
}
