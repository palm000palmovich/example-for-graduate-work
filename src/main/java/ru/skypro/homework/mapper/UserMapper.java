package ru.skypro.homework.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import ru.skypro.homework.dto.Register;
import ru.skypro.homework.dto.UpdateUser;
import ru.skypro.homework.dto.UserDto;
import ru.skypro.homework.model.User;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mapping(source = "firstName", target = "name")
    @Mapping(source = "emailAddress", target = "email")
    UserDto toUserDto(User user);

    @Mapping(target = "id", source = "pk")
    @Mapping(target = "email", source = "username")
    UserDto userToDto(User user);

    @Mapping(target = "id", ignore = true)
    User registerToUser(Register register);

    @Mapping(target = "id", ignore = true)
    User updateUserToUser(UpdateUser updateUser);

}