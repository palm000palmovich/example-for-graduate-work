package ru.skypro.homework.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.Ads;
import ru.skypro.homework.dto.CreateOrUpdateAd;
import ru.skypro.homework.dto.Ad;
import ru.skypro.homework.dto.ExtendedAd;
import ru.skypro.homework.service.AdService;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequestMapping("/ads")
public class AdController {
    private final AdService adService;

    public AdController(AdService adService) {
        this.adService = adService;
    }

    @GetMapping
    public Ads getAllAds() {
        List<Ad> ads = new ArrayList<>();
        return new Ads(ads);
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Ad createAdd(@Valid @RequestPart("properties") CreateOrUpdateAd properties,
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
    public Ad updateAd(@PathVariable Integer id, @Valid @RequestBody CreateOrUpdateAd updatedAdvertisement) {
        return new Ad();
    }

    @GetMapping("/me")
    public Ads getAdsMe() {
        List<Ad> ads = new ArrayList<>();
        return new Ads(ads);
    }

    @PatchMapping("/{id}/image")
    public MultipartFile updateAdImage(@PathVariable Integer id,
                                       @RequestPart("image") MultipartFile image) {
        return image;
    }
}
