package ru.skypro.homework.userMappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;
import ru.skypro.homework.dto.Register;
import ru.skypro.homework.model.UserEntity;

@Mapper(componentModel = "spring")
public interface RegisterMapper {
    RegisterMapper INSTANCE = Mappers.getMapper(RegisterMapper.class);

            @Mapping(target = "pk", ignore = true)
            @Mapping(target = "image", ignore = true)
    UserEntity toEntity(Register register);
}
