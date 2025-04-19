package ru.skypro.homework.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.dto.User;
import ru.skypro.homework.exception.EmptyPasswordException;
import ru.skypro.homework.service.UserService;

import java.util.Map;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/set_password")
    public NewPassword setPassword() {
       return new NewPassword();
    }

    @GetMapping("/me")
    public User getInfoAboutCurrentUser() {
        return new User();
    }
}



