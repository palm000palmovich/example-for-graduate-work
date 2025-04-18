package ru.skypro.homework.service;

import ru.skypro.homework.dto.User;

import java.util.Map;

public interface UserService {
    Map<String, String> setPassword(String newPassword);

    Map<String, String> getInfoAboutCurrentUser();

    User initFirstNameAndLastName();
}
