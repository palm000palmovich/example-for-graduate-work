package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.User;
import ru.skypro.homework.exception.EmptyPasswordException;
import ru.skypro.homework.exception.PasswordEqualsException;
import ru.skypro.homework.exception.UserNotFoundException;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.UserService;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private User user;

    /**
     * Шифрует новый пароль и сохраняет его у объекта user
     *
     * @param newPassword
     * @return response об успешном обновлении пароля
     * @throws PasswordEqualsException, если старый и новый пароли одинаковые
     * @throws EmptyPasswordException,  если новый пароль - пустой
     */
    @Override
    public Map<String, String> setPassword(String newPassword) {
        user = initFirstNameAndLastName();
        String currentPassword = user.getPassword();

        if (currentPassword.equals(newPassword)) {
            throw new PasswordEqualsException();
        }
        if (newPassword != null) {
            user.setPassword(passwordEncoder.encode(newPassword));
            userRepository.save(user);
        } else {
            throw new EmptyPasswordException();
        }
        Map<String, String> response = new HashMap<>();
        response.put("currentPassword", currentPassword);
        response.put("newPassword", newPassword);
        return response;
    }

    /**
     * Получает данные и пользователе и заполняет мапу информацией о нем
     *
     * @return info
     */
    @Override
    public Map<String, String> getInfoAboutCurrentUser() {
        user = initFirstNameAndLastName();
        Map<String, String> info = new HashMap<>();
        info.put("email", user.getEmail());
        info.put("firstName", user.getFirstName());
        info.put("lastName", user.getLastName());
        info.put("phone", user.getPhone());
        info.put("role", user.getRole().name());
        info.put("image", user.getImage());
        return info;
    }

    /**
     * Метод извлекает имя и фамилию текущего пользователя из SecurityContextHolder,
     * ищет в репозитории пользователя по имени и фамилии
     *
     * @return user
     * @throws UserNotFoundException, если пользователь не был найден
     */
    @Override
    public User initFirstNameAndLastName() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        String[] names = username.split(" ");
        user = userRepository.findByFirstNameAndLastName(names[0], names[1])
                .orElseThrow(UserNotFoundException::new);
        return user;
    }
}
