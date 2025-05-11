package ru.skypro.homework.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.skypro.homework.exception.AvatarNotFoundException;
import ru.skypro.homework.model.Avatar;
import ru.skypro.homework.service.impl.AvatarServiceImpl;

@RestController
@RequestMapping(path = "/ads")
public class AdImageController {
    private final AvatarServiceImpl avatarServiceImpl;
    private Logger logger = LoggerFactory.getLogger(ImageController.class);

    public AdImageController(AvatarServiceImpl avatarServiceImpl) {
        this.avatarServiceImpl = avatarServiceImpl;
    }

    @GetMapping("/{id}/images")
    public ResponseEntity<byte[]> getImage(@PathVariable Integer id) {
        Avatar avatar = new Avatar();
        try {
            avatar = avatarServiceImpl.getAdImageById(id);
        } catch (AvatarNotFoundException e) {
            logger.error("Avatar of Ad with id " + id + " is not found.");
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.valueOf(avatar.getMediaType())); // Установите правильный MIME-тип, если известно
        headers.setContentLength(avatar.getFileSize());
        return new ResponseEntity<>(avatar.getData(), headers, HttpStatus.OK);
    }
}
