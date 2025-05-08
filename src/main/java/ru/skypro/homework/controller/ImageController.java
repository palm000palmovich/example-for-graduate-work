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
import ru.skypro.homework.model.UserAvatar;
import ru.skypro.homework.service.impl.AvatarServiceImpl;

@RestController
@RequestMapping(path = "/images")
public class ImageController {
    private final AvatarServiceImpl avatarService;
    private Logger logger = LoggerFactory.getLogger(ImageController.class);

    public ImageController(AvatarServiceImpl avatarService){
        this.avatarService = avatarService;
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<byte[]> getUsersAvatar(@PathVariable("id") Integer id){
        UserAvatar userAvatar = new UserAvatar();

        try{
            userAvatar = avatarService.getAvaById(id);
        } catch(AvatarNotFoundException e){
            logger.error("Avatar with id " + id + " is not found.");
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.valueOf(userAvatar.getMediaType()));
        headers.setContentLength(userAvatar.getFileSize());

        return new ResponseEntity<>(userAvatar.getData(), headers, HttpStatus.OK);
    }
}
