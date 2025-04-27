package ru.skypro.homework.mapper;

import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.skypro.homework.dto.AdDto;
import ru.skypro.homework.dto.CreateOrUpdateAd;
import ru.skypro.homework.dto.ExtendedAd;
import ru.skypro.homework.model.Ad;
import ru.skypro.homework.model.User;

import java.util.List;

@Mapper
public interface AdMapper {

    @Mapping(source = "user.id", target = "author")
    AdDto toDto(Ad ad);

    @Mapping(target = "user.id", source = "author")
    Ad toEntity(CreateOrUpdateAd createOrUpdateAd, @Context User user);

    List<AdDto> toDtoList(List<Ad> ads);

    @Mapping(source = "user.firstName", target = "authorFirstName")
    @Mapping(source = "user.lastName", target = "authorLastName")
    ExtendedAd toExtendedAd(Ad ad);

}
