package ru.dorogova.bg_world.service.implementation;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.dorogova.bg_world.model.BoardGame;
import ru.dorogova.bg_world.model.User;
import ru.dorogova.bg_world.repository.BoardGameRepository;
import ru.dorogova.bg_world.service.BoardGameService;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class BoardGameServiceImpl implements BoardGameService {
    /**
     * Интерфейс, позволяющий работать с данными
     */
    private final BoardGameRepository bgRepository;
    private final UserServiceImpl userService;


    /**
     * Добавление новой настольной игры в коллекцию
     */
    @Override
    public BoardGame addBoardGame(BoardGame boardGame) {
        return bgRepository.save(boardGame);
    }

    /**
     * Метод получения всех настольных игр
     */
    @Override
    public List<BoardGame> getAllBoardGames() {
        return bgRepository.findAll();
    }

    /**
     * Получение списка настольных игр пользователя
     * @param userName имя пользователя
     * @return список настольных игр пользователя
     */
    @Override
    public List<BoardGame> getBoardGamesByUserName(String userName) {
        User user = userService.findByName(userName);
        if (user != null) {
            return user.getBoardGames();
        } else {
            return List.of();
        }
    }

    /**
     * Поиск настольной игры по её идентификатору
     */
    @Override
    public Optional<BoardGame> getBoardGameById(Long id) {
        return bgRepository.findById(id);
    }

    /**
     * Метод внесения изменений в запись о настольной игре
     * @param id по которому мы ищем игру для внесения изменений
     * @param bg данные для внесения изменений
     * @return настольная игра с обновленной информацией
     */
    @Override
    public BoardGame editBoardGame(Long id, BoardGame bg) {
        Optional<BoardGame> optionalBG = bgRepository.findById(id);
        if(optionalBG.isPresent()){
            BoardGame boardGame = optionalBG.get();
            boardGame.setTitle(bg.getTitle());
            boardGame.setGettingDate(bg.getGettingDate());
            boardGame.setGenre(bg.getGenre());
            return bgRepository.save(boardGame);
        }
        else {
            throw new IllegalArgumentException("Запрашиваемая настольная игра не занесена в каталог");
        }
    }

    /**
     * Метод удаления настольной игры
     */
    @Override
    public void deleteBoardGame(Long id) {
        bgRepository.deleteById(id);
    }
}
