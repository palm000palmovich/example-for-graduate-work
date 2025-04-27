package ru.skypro.homework.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.dto.NewPassword;
import ru.skypro.homework.dto.UpdateUser;
import ru.skypro.homework.dto.UserDto;
import ru.skypro.homework.service.impl.UserServiceImpl;

@RestController
@CrossOrigin(value = "http://localhost:3000")
@RequestMapping("/users")
@Validated
public class UserController {
    private final UserServiceImpl userService;

    @Autowired
    public UserController(UserServiceImpl userService){
        this.userService = userService;
    }

    @PostMapping("/set_password")
    public NewPassword setPassword(@RequestBody NewPassword newPassword) {
        userService.setPassword(newPassword);
        return newPassword;
    }

    @GetMapping("/me")
    public UserDto getInfoAboutCurrentUser(@PathVariable Long id) {
        return new UserDto();
    }

    @PatchMapping("/me")
    public UpdateUser updateUser(@RequestBody UpdateUser updateUser){
        return new UpdateUser();
    }

    @PatchMapping("/me/image")
    public String updateUsersImage(){
        return "Все харащо!";
    }

}
