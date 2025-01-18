package ru.dorogova.bg_world.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.dorogova.bg_world.model.User;
import ru.dorogova.bg_world.service.implementation.UserServiceImpl;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserServiceImpl userService;

    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<User>> findAllUsers(){
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserByName(String name){
        return new ResponseEntity<>(userService.findByName(name), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<User> addUser(User user){
        return new ResponseEntity<>(userService.addUser(user), HttpStatus.CREATED);
    }

}
