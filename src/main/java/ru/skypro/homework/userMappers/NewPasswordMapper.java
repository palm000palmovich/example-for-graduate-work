package ru.skypro.homework.userMappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import ru.skypro.homework.dto.NewPassword;
import ru.skypro.homework.model.UserEntity;

@Mapper(componentModel = "spring")
public interface NewPasswordMapper {
    NewPasswordMapper INSTANCE = Mappers.getMapper(NewPasswordMapper.class);

    @Mapping(target = "password", source = "newPassword")
    UserEntity toEntity(NewPassword newPassword);
}
