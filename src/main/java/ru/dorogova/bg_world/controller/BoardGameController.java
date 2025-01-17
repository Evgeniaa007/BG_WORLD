package ru.dorogova.bg_world.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.dorogova.bg_world.model.BoardGame;
import ru.dorogova.bg_world.service.implementation.BoardGameServiceImpl;
import ru.dorogova.bg_world.service.implementation.UserServiceImpl;

import java.util.List;

@RestController
@RequestMapping("/bg")
public class BoardGameController {

    private final BoardGameServiceImpl bgService;
    private final UserServiceImpl userService;

    public BoardGameController(BoardGameServiceImpl bgService, UserServiceImpl userService) {
        this.bgService = bgService;
        this.userService = userService;
    }

    @GetMapping("/{name}")
    public ResponseEntity<List<BoardGame>> getUserGames(@PathVariable String name) {
        return new ResponseEntity<>(bgService.getByOwner(name), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<BoardGame> addBoardGame(@RequestBody BoardGame boardGame) {
        bgService.addBoardGame(boardGame);
        return new ResponseEntity<>(boardGame, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BoardGame> editBoardGame(@PathVariable Long id, @RequestBody BoardGame boardGame){
        return new ResponseEntity<>(bgService.editBoardGame(id, boardGame), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBoardGame(@PathVariable Long id) {
        bgService.deleteBoardGame(id);
        return ResponseEntity.ok().build();
    }
}







