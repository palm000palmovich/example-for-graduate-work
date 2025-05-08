package ru.skypro.homework.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.AdDto;
import ru.skypro.homework.dto.AdsDto;
import ru.skypro.homework.dto.CreateOrUpdateAd;
import ru.skypro.homework.dto.ExtendedAd;
import ru.skypro.homework.exception.ForbiddenAccesException;
import ru.skypro.homework.exception.UnauthorizedAccesException;
import ru.skypro.homework.model.Ad;
import ru.skypro.homework.repository.AdRepository;
import ru.skypro.homework.service.impl.AdServiceImpl;
import ru.skypro.homework.service.impl.FileStorageService;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.io.IOException;
import java.util.Map;

@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequestMapping("/ads")
@Validated
public class AdController {

    private final AdServiceImpl adService;
    private final FileStorageService fileStorageService;
    private final AdRepository adRepository;

    public AdController(AdServiceImpl adService, FileStorageService fileStorageService, AdRepository adRepository) {
        this.adService = adService;
        this.fileStorageService = fileStorageService;
        this.adRepository = adRepository;
    }

    @GetMapping
    public AdsDto getAllAds() {
        return adService.getAllAds();
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Ad> createAdd(@Valid @RequestPart("properties") CreateOrUpdateAd properties,
                                        @RequestPart("image") MultipartFile image) {
        try {
            Ad ad = adService.createAdd(properties, image);
            return ResponseEntity.status(HttpStatus.CREATED).body(ad);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getAdById(@PathVariable Integer id) {
        try {
            ExtendedAd extendedAd = adService.getAddById(id);
            return ResponseEntity.ok(extendedAd);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("error", "Ad not found"));
        } catch (UnauthorizedAccesException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAd(@PathVariable Integer id) {
        try {
            adService.deleteAddById(id);
            return ResponseEntity.noContent().build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (UnauthorizedAccesException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        } catch (ForbiddenAccesException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
    }


    @PatchMapping("/{id}")
    public ResponseEntity<AdDto> updateAd(@PathVariable Integer id,
                                          @Valid @RequestBody CreateOrUpdateAd updatedAdvertisement) {
        Ad ad = adRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        ad.setTitle(updatedAdvertisement.getTitle());
        ad.setDescription(updatedAdvertisement.getDescription());
        ad.setPrice(updatedAdvertisement.getPrice());
        adRepository.save(ad);

        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("/me")
    public ResponseEntity<AdsDto> getAdsMe() {
        return ResponseEntity.status(HttpStatus.OK).body(adService.getAdsUsers());
    }

    @PatchMapping(value = "/{id}/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> updateAdImage(@PathVariable Integer id,
                                           @RequestPart("image") MultipartFile image) throws IOException {
        fileStorageService.uploadImage(id, image);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
