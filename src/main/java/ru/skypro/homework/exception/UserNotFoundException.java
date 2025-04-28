package ru.skypro.homework.exception;

import ru.skypro.homework.model.User;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(User user) {
        super("id: " + user.getId() + " username: " + user.getUsername() + " not found");
    }
}