package ru.dorogova.bg_world.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import ru.dorogova.bg_world.service.BoardGameServiceImpl;

@Controller
@AllArgsConstructor
public class BoardGameController {

    private final BoardGameServiceImpl bgService;

    @GetMapping("/home")
    String home() {
        return "homepage";
    }

}
