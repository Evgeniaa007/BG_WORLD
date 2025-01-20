/*package ru.dorogova.bg_world.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.dorogova.bg_world.model.BoardGame;
import ru.dorogova.bg_world.model.User;
import ru.dorogova.bg_world.service.implementation.BoardGameServiceImpl;
import ru.dorogova.bg_world.service.implementation.UserServiceImpl;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserServiceImpl userService;
    private final BoardGameServiceImpl boardGameService;

    public UserController(UserServiceImpl userService, BoardGameServiceImpl boardGameService) {
        this.userService = userService;
        this.boardGameService = boardGameService;
    }

    @GetMapping
    public ResponseEntity<List<User>> findAllUsers(){
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }

    @GetMapping("/{name}")
    public ResponseEntity<User> getUserByName(@PathVariable String name){
        return new ResponseEntity<>(userService.findByName(name), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<User> addUser(@RequestBody User user){
        return new ResponseEntity<>(userService.addUser(user), HttpStatus.CREATED);
    }

}*/



