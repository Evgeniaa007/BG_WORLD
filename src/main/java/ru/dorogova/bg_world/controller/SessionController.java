/*package ru.dorogova.bg_world.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.dorogova.bg_world.model.BoardGame;
import ru.dorogova.bg_world.model.Session;
import ru.dorogova.bg_world.model.User;
import ru.dorogova.bg_world.service.implementation.BoardGameServiceImpl;
import ru.dorogova.bg_world.service.implementation.SessionServiceImpl;
import ru.dorogova.bg_world.service.implementation.UserServiceImpl;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/sessions")
public class SessionController {

    private final SessionServiceImpl sessionService;
    private final BoardGameServiceImpl boardGameService;

    public SessionController(SessionServiceImpl sessionService,
                             BoardGameServiceImpl boardGameService, UserServiceImpl userService) {
        this.sessionService = sessionService;
        this.boardGameService = boardGameService;
    }

    @GetMapping
    public ResponseEntity<List<Session>> getAllSessions(){
        return new ResponseEntity<>(sessionService.getAllSessions(), HttpStatus.OK);
    }

    @GetMapping("/{id}/bgSessions")
    public ResponseEntity<List<Session>> getBoardGameSessions(@PathVariable Long id) {
        List<Session> sessions = sessionService.getBoardGameSessions(id);
        return new ResponseEntity<>(sessions, HttpStatus.OK);
    }

    @PostMapping("/{boardGameId}")
    public ResponseEntity<Session> addSession(@RequestBody Session session, @PathVariable Long boardGameId) {
        Optional<BoardGame> optionalBoardGame = boardGameService.getBoardGameById(boardGameId);
        if (optionalBoardGame.isPresent()) {
            BoardGame boardGame = optionalBoardGame.get();
            boardGame.addSession(session);
            sessionService.addSession(session);
            return new ResponseEntity<>(session, HttpStatus.CREATED);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Session> editSession(@PathVariable Long id, @RequestBody Session session){
        return new ResponseEntity<>(sessionService.editSession(id, session), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSession(@PathVariable Long id) {
        Optional<Session>optionalSession = sessionService.getSessionById(id);
        if(optionalSession.isPresent()){
            Session session = optionalSession.get();
            BoardGame boardGame = session.getBoardGame();
            if(boardGame != null){
                boardGame.getSessions().remove(session);
            }
            sessionService.deleteSession(id);
            return ResponseEntity.ok().build();
        }
        else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

    }

}
*/