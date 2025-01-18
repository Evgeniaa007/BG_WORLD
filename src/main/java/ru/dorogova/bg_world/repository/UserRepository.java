package ru.dorogova.bg_world.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.dorogova.bg_world.model.User;
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByName(String name);
}
