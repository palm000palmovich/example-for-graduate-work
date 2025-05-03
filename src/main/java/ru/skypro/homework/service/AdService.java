package ru.skypro.homework.service;

import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.AdDto;
import ru.skypro.homework.dto.AdsDto;
import ru.skypro.homework.dto.CreateOrUpdateAd;
import ru.skypro.homework.dto.ExtendedAd;
import ru.skypro.homework.model.Ad;

public interface AdService {
    AdsDto getAllAds();

    Ad createAdd(CreateOrUpdateAd createOrUpdateAd, MultipartFile image);

    ExtendedAd getAddById(Integer id);

    void deleteAddById(Integer id);

    AdDto updateAdById(Integer id, CreateOrUpdateAd createOrUpdateAd);

    AdsDto getAdsUsers();

    void updateAdImageById(Integer id, MultipartFile image);
}
