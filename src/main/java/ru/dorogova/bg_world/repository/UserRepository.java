package ru.dorogova.bg_world.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.dorogova.bg_world.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByName(String name);
}
