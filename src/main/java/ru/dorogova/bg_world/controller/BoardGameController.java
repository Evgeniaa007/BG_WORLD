/*package ru.dorogova.bg_world.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.dorogova.bg_world.model.BoardGame;
import ru.dorogova.bg_world.model.User;
import ru.dorogova.bg_world.service.implementation.BoardGameServiceImpl;
import ru.dorogova.bg_world.service.implementation.SessionServiceImpl;
import ru.dorogova.bg_world.service.implementation.UserServiceImpl;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/bg")
public class BoardGameController {

    private final BoardGameServiceImpl bgService;

    private final SessionServiceImpl sessionService;

    private final UserServiceImpl userService;

    public BoardGameController(BoardGameServiceImpl bgService,
                               SessionServiceImpl sessionService, UserServiceImpl userService) {
        this.bgService = bgService;
        this.sessionService = sessionService;
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<BoardGame>> getAllBoardGames(){
        return new ResponseEntity<>(bgService.getAllBoardGames(), HttpStatus.OK);
    }

    @GetMapping("/{userName}/collection")
    public ResponseEntity<List<BoardGame>> getUserGames(@PathVariable String userName) {
        List<BoardGame> boardGames = bgService.getBoardGamesByUserName(userName);
        return new ResponseEntity<>(boardGames, HttpStatus.OK);
    }

    @PostMapping("/{userName}")
    public ResponseEntity<BoardGame> addBoardGame(@RequestBody BoardGame boardGame,@PathVariable String userName) {
        User user = userService.findByName(userName);
        user.addBoardGame(boardGame);
        bgService.addBoardGame(boardGame);
        return new ResponseEntity<>(boardGame, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BoardGame> editBoardGame(@PathVariable Long id, @RequestBody BoardGame boardGame){
        return new ResponseEntity<>(bgService.editBoardGame(id, boardGame), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBoardGame(@PathVariable Long id) {
        Optional<BoardGame> optionalBoardGame = bgService.getBoardGameById(id);
        if (optionalBoardGame.isPresent()) {
            BoardGame boardGame = optionalBoardGame.get();
            User user = boardGame.getUser();
            if (user != null) {
                user.getBoardGames().remove(boardGame);
            }
            bgService.deleteBoardGame(id);
            return ResponseEntity.ok().build(); // Успех
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
*/
