package ru.skypro.homework.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.exception.EmptyPasswordException;
import ru.skypro.homework.service.UserService;

import java.util.Map;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    /**
     * Обновление пароля
     *
     * @param request тело HTTP-запроса в формате json будет преобразовано в Map<String, String>
     * Извлекается новый пароль
     * @throws EmptyPasswordException, если новый пароль - пустой
     */
    @PostMapping("/set_password")
    public Map<String, String> setPassword(@RequestBody Map<String, String> request) {
        String newPassword = request.get("newPassword");
        return userService.setPassword(newPassword);
    }

    /**
     * Получение информации об авторизованном пользователе
     */
    @GetMapping("/me")
    public Map<String, String> getInfoAboutCurrentUser() {
        return userService.getInfoAboutCurrentUser();
    }
}



