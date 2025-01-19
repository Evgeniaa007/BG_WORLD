package ru.dorogova.bg_world.service.implementation;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.dorogova.bg_world.model.BoardGame;
import ru.dorogova.bg_world.model.User;
import ru.dorogova.bg_world.repository.UserRepository;
import ru.dorogova.bg_world.service.UserService;

import java.util.List;
@AllArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    UserRepository userRepository;

    @Override
    public User addUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User findByName(String name) {
        return userRepository.findByName(name);
    }


}
