package ru.skypro.homework.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.skypro.homework.model.AdImage;
import ru.skypro.homework.service.impl.FileStorageService;

@RestController
@RequestMapping(path = "/ads")
public class AdImageController {
    private final FileStorageService fileStorageService;

    public AdImageController(FileStorageService fileStorageService) {
        this.fileStorageService = fileStorageService;
    }

    @GetMapping("/{id}/images")
    public ResponseEntity<byte[]> getImage(@PathVariable Integer id) {
        try {
            byte[] imageData = fileStorageService.getImageData(id);
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.IMAGE_JPEG); // Установите правильный MIME-тип, если известно
            return new ResponseEntity<>(imageData, headers, HttpStatus.OK);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
}
