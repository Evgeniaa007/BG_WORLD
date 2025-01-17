package ru.dorogova.bg_world.service;

import ru.dorogova.bg_world.model.User;

import java.util.List;

public interface UserService {
    User addUser(User user);

    List<User> findAllUsers();

    User findByName(String name);
}
