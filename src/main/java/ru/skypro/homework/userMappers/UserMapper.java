package ru.skypro.homework.userMappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;
import ru.skypro.homework.dto.User;
import ru.skypro.homework.model.UserEntity;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mapping(target = "pk", ignore = true)
    @Mapping(target = "password", ignore = true)
    @Mapping(source = "email", target = "username")
    UserEntity toEntity(User user);

    @Mapping(target = "id", source = "pk")
    @Mapping(target = "email", source = "username")
    User toDto(UserEntity userEntity);
}
