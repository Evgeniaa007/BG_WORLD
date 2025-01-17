package ru.dorogova.bg_world.service.implementation;

import ru.dorogova.bg_world.model.Session;
import ru.dorogova.bg_world.repository.SessionRepository;
import ru.dorogova.bg_world.service.SessionService;

import java.util.List;
import java.util.Optional;

public class SessionServiceImpl implements SessionService {

    SessionRepository sessionRepository;

    @Override
    public Session addSession(Session session) {
        return sessionRepository.save(session);
    }

    @Override
    public List<Session> getAllSessions() {
        return sessionRepository.findAll();
    }

    @Override
    public List<Session> getSessionsByUser(Long id) {
        return sessionRepository.getSessionsByUser(id);
    }

    @Override
    public Optional<Session> getSessionById(Long id) {
        return sessionRepository.findById(id);
    }

    @Override
    public Session editSession(Long id, Session session) {
        return null;
    }

    @Override
    public void deleteSession(Long id) {
        sessionRepository.deleteById(id);
    }
}
