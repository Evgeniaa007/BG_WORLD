package ru.dorogova.bg_world.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.dorogova.bg_world.model.Session;

import java.util.List;

public interface SessionRepository extends JpaRepository<Session, Long> {
    List<Session> getSessionsByUser(Long userId);
}
