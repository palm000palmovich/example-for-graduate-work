package ru.skypro.homework.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.NewPassword;
import ru.skypro.homework.dto.UpdateUser;
import ru.skypro.homework.dto.UserDto;
import ru.skypro.homework.service.impl.AvatarServiceImpl;
import ru.skypro.homework.service.impl.UserServiceImpl;

import javax.validation.Valid;
import java.io.IOException;


@RestController
@RequestMapping("/users")
@Validated
public class UserController {
    private final UserServiceImpl userService;
    private final AvatarServiceImpl avatarService;
    private Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    public UserController(UserServiceImpl userService,
                          AvatarServiceImpl avatarService){
        this.userService = userService;
        this.avatarService = avatarService;
    }

    @PostMapping("/set_password")
    public ResponseEntity<?> setPassword(@Valid @RequestBody NewPassword newPassword,
                                      Authentication authentication) {

        boolean result = userService.setPassword(newPassword, authentication.getName());

        if (result) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Пользователь не найден");
        }
    }

    @GetMapping("/me")
    public ResponseEntity<UserDto> getInfoAboutCurrentUser(Authentication authentication) {
        String userName = authentication.getName();

        UserDto userDto = userService.getInfo(userName);

        if (userDto == null){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        return ResponseEntity.ok(userDto);
    }

    @PatchMapping("/me")
    public ResponseEntity<UpdateUser> updateUser(@Valid @RequestBody UpdateUser updateUser,
                                 Authentication authentication){

        String userName = authentication.getName();

        if (!userService.updateUser(userName, updateUser)){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        return ResponseEntity.ok(updateUser);
    }

    @PatchMapping("/me/image")
    public ResponseEntity<?> uploadUserImage(
            @RequestParam("image") MultipartFile file,
            Authentication authentication) throws IOException {

        String userName = authentication.getName();

        try{
            avatarService.uploadAvatar(userName, file);
        } catch (IOException e){
            logger.error("Troubles with file.");
        }

        return ResponseEntity.ok().build();
    }
}