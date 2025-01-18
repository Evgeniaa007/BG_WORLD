package ru.dorogova.bg_world.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.dorogova.bg_world.model.BoardGame;

import java.util.List;
@Repository
public interface BoardGameRepository extends JpaRepository<BoardGame, Long> {
    /**
     * Метод, позволяющий вывести список игр, которые
     * принадлежат определенному пользователю в соответствии с его ID
     */
    List<BoardGame> getByUserName(String userName);
}
