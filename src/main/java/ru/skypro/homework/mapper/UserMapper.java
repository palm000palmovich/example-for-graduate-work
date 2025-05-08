package ru.skypro.homework.mapper;

import org.springframework.stereotype.Component;
import ru.skypro.homework.dto.UserDto;
import ru.skypro.homework.model.User;

@Component
public class UserMapper {

    //User -> UserDto
    public UserDto toUserDto(User user){
        return new UserDto(user.getId(), user.getUsername(), user.getFirstName(),
                user.getLastName(), user.getPhone(), user.getRole(), user.getImage());
    }
}