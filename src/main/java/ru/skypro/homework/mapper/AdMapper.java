package ru.skypro.homework.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.skypro.homework.dto.AdDto;
import ru.skypro.homework.dto.AdsDto;
import ru.skypro.homework.dto.CreateOrUpdateAd;
import ru.skypro.homework.dto.ExtendedAd;
import ru.skypro.homework.model.Ad;

import java.util.Arrays;
import java.util.List;

@Mapper(componentModel = "spring")
public interface AdMapper {

//    @Mapping(source = "id", target = "pk")
//    @Mapping(source = "user.id", target = "author")
    default AdDto toAdDto(Ad ad){
        AdDto adDto = new AdDto();
        adDto.setPk(ad.getId());
        adDto.setTitle(ad.getTitle());
        adDto.setAuthor(ad.getUser().getId());
        adDto.setPrice(ad.getPrice());
        if (ad.getImage() != null) {
            adDto.setImage(ad.getImage());
        }
        return adDto;
    }

    default AdsDto toAdsDto(List<Ad> ads) {
        AdsDto adsDto = new AdsDto();
        adsDto.setCount(ads.size());
        adsDto.setResults(ads.stream()
                .map(this::toAdDto)
                .toList());
        return adsDto;
    }

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "commentsList", ignore = true)
    @Mapping(source = "title", target = "title")
    @Mapping(source = "price", target = "price")
    @Mapping(source = "description", target = "description")
    @Mapping(target = "image", ignore = true)
    Ad toAd(CreateOrUpdateAd createOrUpdateAd);

    @Mapping(source = "user.id", target = "pk")
    @Mapping(source = "user.firstName", target = "authorFirstName")
    @Mapping(source = "user.lastName", target = "authorLastName")
    @Mapping(source = "ad.description", target = "description")
    @Mapping(source = "user.username", target = "email")
    @Mapping(source = "user.image", target = "image")
    @Mapping(source = "user.phone", target = "phone")
    @Mapping(source = "ad.price", target = "price")
    @Mapping(source = "ad.title", target = "title")
    ExtendedAd toExtendedAd(Ad ad);

}
