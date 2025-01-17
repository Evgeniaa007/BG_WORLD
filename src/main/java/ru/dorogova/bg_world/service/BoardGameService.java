package ru.dorogova.bg_world.service;

import ru.dorogova.bg_world.model.BoardGame;

import java.util.List;
import java.util.Optional;

public interface BoardGameService {

    BoardGame addBoardGame (BoardGame boardGame);

    List<BoardGame> getAllBoardGames();

    List<BoardGame> getByOwner(Long id);

    Optional<BoardGame> getBGById(Long id);

    BoardGame editBoardGame (Long id, BoardGame bg);

    void deleteBoardGame (Long id);

}
