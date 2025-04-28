package ru.skypro.homework.controller;

import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.AdDto;
import ru.skypro.homework.dto.AdsDto;
import ru.skypro.homework.dto.CreateOrUpdateAd;
import ru.skypro.homework.dto.ExtendedAd;
import ru.skypro.homework.service.impl.AdServiceImpl;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequestMapping("/ads")
@Validated
public class AdController {
    private final AdServiceImpl adService;

    public AdController(AdServiceImpl adService) {
        this.adService = adService;
    }

    @GetMapping
    public AdsDto getAllAds() {
        List<AdDto> ads = new ArrayList<>();
        return new AdsDto(ads);
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public AdDto createAdd(@Valid @RequestPart("properties") CreateOrUpdateAd properties,
                           @RequestPart("image") MultipartFile image) {
        return null;
    } //один из вариантов

    @GetMapping("/{id}")
    public ExtendedAd getAdById(@PathVariable("id") Integer id) {
        return new ExtendedAd();
    }

    @DeleteMapping("/{id}")
    public void deleteAd(@PathVariable("id") Integer id) {
    }


    @PatchMapping("/{id}")
    public AdDto updateAd(@PathVariable Integer id, @Valid @RequestBody CreateOrUpdateAd updatedAdvertisement) {
        return new AdDto();
    }

    @GetMapping("/me")
    public AdsDto getAdsMe() {
        List<AdDto> ads = new ArrayList<>();
        return new AdsDto(ads);
    }

    @PatchMapping("/{id}/image")
    public MultipartFile updateAdImage(@PathVariable Integer id,
                                       @RequestPart("image") MultipartFile image) {
        return image;
    }
}
