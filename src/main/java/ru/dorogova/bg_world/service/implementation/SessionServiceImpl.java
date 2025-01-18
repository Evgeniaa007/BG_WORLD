package ru.dorogova.bg_world.service.implementation;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.dorogova.bg_world.model.BoardGame;
import ru.dorogova.bg_world.model.Session;
import ru.dorogova.bg_world.repository.SessionRepository;
import ru.dorogova.bg_world.service.SessionService;

import java.util.List;
import java.util.Optional;
@AllArgsConstructor
@Service
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
/*
    @Override
    public List<Session> getSessionsByUserId(Long id) {
        return sessionRepository.getSessionsByUserId(id);
    }*/

    @Override
    public Optional<Session> getSessionById(Long id) {
        return sessionRepository.findById(id);
    }

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
            return sessionRepository.save(session);
        }
        else {
            throw new IllegalArgumentException("Запрашиваемая сессия не существует.");
        }
    }

    @Override
    public void deleteSession(Long id) {
        sessionRepository.deleteById(id);
    }
}
