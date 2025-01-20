package ru.dorogova.bg_world.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.dorogova.bg_world.model.BoardGame;
import ru.dorogova.bg_world.model.Session;
import ru.dorogova.bg_world.model.User;
import ru.dorogova.bg_world.service.implementation.BoardGameServiceImpl;
import ru.dorogova.bg_world.service.implementation.SessionServiceImpl;
import ru.dorogova.bg_world.service.implementation.UserServiceImpl;

import java.util.List;
import java.util.Optional;

/**
 * единый контроллер для работы с html страницами
 */
@Controller
@AllArgsConstructor
public class AppController {

    private final UserServiceImpl userService;
    private final BoardGameServiceImpl boardGameService;
    private final SessionServiceImpl sessionService;

    /**
     * начальная страница
     */
    @GetMapping("/")
    public String homePage(Model model) {
        model.addAttribute("newUser", new User());
        return "home";
    }

    /**
     * Добавление пользователя
     * @param user новый пользователь
     * @return страница main
     */
    @PostMapping("/addUser")
    public String addUser(@ModelAttribute User user) {
        userService.addUser(user);
        return "redirect:/main";
    }

    /**
     * страница, на которой отображается список пользователей
     * и список сыгранных партий
     */
    @GetMapping("/main")
    public String mainPage(Model model) {
        List<User> users = userService.getAllUsers();
        List<Session> recentSessions = sessionService.getAllSessions(); // Можно добавить сортировку по дате
        model.addAttribute("users", users);
        model.addAttribute("recentSessions", recentSessions);
        return "main";
    }

    /**
     * страница с коллекцией настольных игр пользователя
     */
    @GetMapping("/user/{userName}")
    public String userCollection(@PathVariable String userName, Model model) {
        User user = userService.findByName(userName);
        model.addAttribute("user", user);
        model.addAttribute("newBoardGame", new BoardGame());
        return "user-collection";
    }

    /**
     * Добавление настольной игры
     * @param userName имя пользователя, которому добавояется настольная игра
     * @param boardGame настольная игра
     * @return переход на страницу коллекции
     */
    @PostMapping("/user/{userName}/addGame")
    public String addBoardGame(@PathVariable String userName, @ModelAttribute BoardGame boardGame) {
        User user = userService.findByName(userName);
        user.addBoardGame(boardGame);
        boardGameService.addBoardGame(boardGame);
        return "redirect:/user/" + userName;
    }

    /**
     * удаление настольной игры
     */
    @GetMapping("deleteGame/{gameId}")
    public String deleteBoardGame(@PathVariable("gameId") Long gameId) {
        Optional<BoardGame> optionalBoardGame = boardGameService.getBoardGameById(gameId);
        if (optionalBoardGame.isPresent()) {
            BoardGame boardGame = optionalBoardGame.get();
            User user = boardGame.getUser();
            if (user != null) {
                user.getBoardGames().remove(boardGame);
            }
        }
        boardGameService.deleteBoardGame(gameId);

        return "redirect:/main";
    }

    /**
     * методы редактирования. Первый метод позволяет получить экземпляр и перейти на страницу
     * внесения изменений, второй - их вносит и сохраняет
     */
    @GetMapping("/editGame/{gameId}")
    public String editBoardGamePage(@PathVariable Long gameId, Model model) {
        Optional<BoardGame> boardGame = boardGameService.getBoardGameById(gameId);
        boardGame.ifPresent(game -> model.addAttribute("boardGame", game));
        return "edit-game";
    }

    @PostMapping("/editGame")
    public String editBoardGame(/*@PathVariable Long gameId, */@ModelAttribute BoardGame boardGame) {
        boardGameService.editBoardGame(boardGame);
        return "redirect:/main";
    }

    /**
     * Страница партий конкретной настольной игры
     */
    @GetMapping("/game/{gameId}/sessions")
    public String gameSessions(@PathVariable Long gameId, Model model) {
        Optional<BoardGame> boardGame = boardGameService.getBoardGameById(gameId);
        if (boardGame.isPresent()) {
            model.addAttribute("boardGame", boardGame.get());
            model.addAttribute("newSession", new Session());
        }
        return "game-sessions";
    }

    /**
     * Добавление партий настольной игры
     */
    @PostMapping("/game/{gameId}/addSession")
    public String addGameSession(@PathVariable Long gameId, @ModelAttribute Session session) {
        Optional<BoardGame> boardGame = boardGameService.getBoardGameById(gameId);
        if (boardGame.isPresent()) {
            BoardGame game = boardGame.get();
            session.setBoardGame(game); // Установить связь между партией и игрой
            sessionService.addSession(session); // Сохранить партию
        }
        return "redirect:/game/" + gameId +"/sessions";
    }

    /**
     * Удаление партий настольной игры
     */
    @GetMapping("/deleteSession/{sessionId}")
    public String deleteGameSession(@PathVariable Long sessionId) {
        Optional<Session>optionalSession = sessionService.getSessionById(sessionId);
        if(optionalSession.isPresent()){
            Session session = optionalSession.get();
            BoardGame boardGame = session.getBoardGame();
            if(boardGame != null){
                boardGame.getSessions().remove(session);
            }
            sessionService.deleteSession(sessionId);
        }
        return "redirect:/main";
    }


    /**
     * Метод редактирования партии, выполненный по аналогии
     * с редактированием настольной игры
     */
    @GetMapping("/editSession/{sessionId}")
    public String editSessionPage(@PathVariable Long sessionId, Model model) {
        Optional<Session> session = sessionService.getSessionById(sessionId);
        session.ifPresent(s -> model.addAttribute("gameSession", s));
        return "edit-session";
    }

    @PostMapping("/editSession")
    public String editSession(/*@PathVariable Long sessionId, */@ModelAttribute Session gameSession) {
        sessionService.editSession(gameSession);
        return "redirect:/main";
    }

}
