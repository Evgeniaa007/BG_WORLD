package ru.dorogova.bg_world.service;

import ru.dorogova.bg_world.model.BoardGame;
import ru.dorogova.bg_world.model.Session;

import java.util.List;
import java.util.Optional;

public interface SessionService {

    Session addSession (Session session);

    List<Session> getAllSessions();

    List<Session> getSessionsByUser(Long id);

    Optional<Session> getSessionById(Long id);

    Session editSession (Long id, Session session);

    void deleteSession (Long id);

}
