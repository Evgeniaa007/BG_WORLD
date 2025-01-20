package ru.dorogova.bg_world.controller;

import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.dorogova.bg_world.model.User;
import ru.dorogova.bg_world.service.implementation.UserServiceImpl;

import java.util.List;

@Controller
public class AuthController {

    private final UserServiceImpl userService;
    private final PasswordEncoder passwordEncoder;

    public AuthController(PasswordEncoder passwordEncoder, UserServiceImpl userService) {
        this.passwordEncoder = passwordEncoder;
        this.userService = userService;
    }

    @GetMapping("/login")
    public String showLoginPage() {
        return "login";
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userService.addUser(user);
        return "redirect:/login";
    }

}


//@PostMapping("/register/save")
//public String registration(@Valid @ModelAttribute("user") UserDto user,
//                           BindingResult result,
//                           Model model) {
//    User existing = userService.findByEmail(user.getEmail());
//    if (existing != null) {
//        result.rejectValue("email", null,
//                "There is already an account registered with that email");
//    }
//    if (result.hasErrors()) {
//        model.addAttribute("user", user);
//        return "register";
//    }
//    userService.saveUserDto(user);
//    return "redirect:/register?success";
//}
