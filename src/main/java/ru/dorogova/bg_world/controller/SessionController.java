package ru.dorogova.bg_world.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.dorogova.bg_world.model.BoardGame;
import ru.dorogova.bg_world.model.Session;
import ru.dorogova.bg_world.service.implementation.BoardGameServiceImpl;
import ru.dorogova.bg_world.service.implementation.SessionServiceImpl;
import ru.dorogova.bg_world.service.implementation.UserServiceImpl;

import java.util.List;

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

    @PostMapping
    public ResponseEntity<Session> addBoardGame(@RequestBody Session session) {
        sessionService.addSession(session);
        return new ResponseEntity<>(session, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Session> editSession(@PathVariable Long id, @RequestBody Session session){
        return new ResponseEntity<>(sessionService.editSession(id, session), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSession(@PathVariable Long id) {
        sessionService.deleteSession(id);
        return ResponseEntity.ok().build();
    }

}
