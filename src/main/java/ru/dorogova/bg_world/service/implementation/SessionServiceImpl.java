package ru.dorogova.bg_world.service.implementation;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.dorogova.bg_world.model.BoardGame;
import ru.dorogova.bg_world.model.Session;
import ru.dorogova.bg_world.model.User;
import ru.dorogova.bg_world.repository.SessionRepository;
import ru.dorogova.bg_world.service.SessionService;

import java.util.List;
import java.util.Optional;
@AllArgsConstructor
@Service
public class SessionServiceImpl implements SessionService {

    SessionRepository sessionRepository;
    BoardGameServiceImpl bgService;

    /**
     * Добавление партии
     */
    @Override
    public Session addSession(Session session) {
        return sessionRepository.save(session);
    }

    /**
     * Получение списка всех партий
     */
    @Override
    public List<Session> getAllSessions() {
        return sessionRepository.findAll();
    }

    /**
     * Получение списка партий по настольной игре
     * @param id настольной игры
     * @return список партий
     */
    @Override
    public List<Session> getBoardGameSessions(Long id) {
        Optional<BoardGame> optionalBoardGame = bgService.getBoardGameById(id); //ищем настольную игру по id с применением сервиса настольных игр
        BoardGame boardGame = optionalBoardGame.get();
        return boardGame.getSessions();
    }

    /**
     * Поиск партии
     * @param id партии
     * @return искомая партия
     */
    @Override
    public Optional<Session> getSessionById(Long id) {
        return sessionRepository.findById(id);
    }

    /**
     * Изменение данных партии
     * @param id партии
     * @param session новые данные для внесения изменений
     * @return обновленная партия
     */
    @Override
    public Session editSession(Long id, Session session) {
        Optional<Session> optionalSession = sessionRepository.findById(id);
        if(optionalSession.isPresent()){
            Session s = optionalSession.get();
            s.setBgName(session.getBgName());
            s.setSessionDate(session.getSessionDate());
            s.setPlayersAmount(session.getPlayersAmount());
            s.setWinner(session.getWinner());
            s.setImpressions(session.getImpressions());
            return sessionRepository.save(s);
        }
        else {
            throw new IllegalArgumentException("Запрашиваемая сессия не существует.");
        }
    }

    /**
     * удаление партии
     * @param id партии
     */
    @Override
    public void deleteSession(Long id) {
        sessionRepository.deleteById(id);
    }
}
