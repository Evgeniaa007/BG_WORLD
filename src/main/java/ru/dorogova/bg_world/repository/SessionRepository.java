package ru.dorogova.bg_world.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.dorogova.bg_world.model.Session;

@Repository
public interface SessionRepository extends JpaRepository<Session, Long> {

}
