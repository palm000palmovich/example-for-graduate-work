package ru.skypro.homework.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.Ads;
import ru.skypro.homework.dto.CreateOrUpdateAd;
import ru.skypro.homework.dto.Ad;
import ru.skypro.homework.dto.ExtendedAd;
import ru.skypro.homework.service.AdService;

import java.util.List;

@RestController
@RequestMapping("/ads")
public class AdController {
    private final AdService adService;

    public AdController(AdService adService) {
        this.adService = adService;
    }

    @GetMapping
    public ResponseEntity<Ads> getAllAds() {
        List<Ad> ads = adService.getAllAdvertisement();
        Ads adsResponse = new Ads(ads.size(), ads);
        return ResponseEntity.ok(adsResponse);
    }

    @PostMapping(consumes = {"multipart/form-data"})
    public ResponseEntity<Ad> createAdd(@RequestPart("properties") CreateOrUpdateAd properties,
                                        @RequestPart("image") MultipartFile image) {

        Ad newAd = adService.createAdvertisement(properties, image);

        return ResponseEntity.status(201).body(newAd);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ExtendedAd> getAdById(@PathVariable("id") Integer id) {
        ExtendedAd ad = adService.getAdvertisementById(id);
        return ResponseEntity.ok(ad);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAd(@PathVariable("id") Integer id) {
        adService.deleteAdvertisement(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Ad> updateAd(@PathVariable Integer id, @RequestBody CreateOrUpdateAd updatedAdvertisement) {
        Ad ad = adService.updateAdvertisement(id, updatedAdvertisement);
        return ResponseEntity.ok(ad);
    }

    @GetMapping("/me")
    public ResponseEntity<Ads> getAdsMe(@AuthenticationPrincipal User user) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        List<Ad> userAds = adService.getUserAds(username);
        Ads adsResponse = new Ads(userAds.size(), userAds);
        return ResponseEntity.ok(adsResponse);
    }

    @PatchMapping("/{id}/image")
    public ResponseEntity<Void> updateAdImage(@PathVariable Integer id,
                                              @RequestParam("image") MultipartFile file) {
        adService.updateImage(id, file);
        return ResponseEntity.ok().build();
    }
}
