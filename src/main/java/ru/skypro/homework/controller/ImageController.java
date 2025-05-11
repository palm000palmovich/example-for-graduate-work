package ru.skypro.homework.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.skypro.homework.exception.AvatarNotFoundException;
import ru.skypro.homework.model.Avatar;
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
        Avatar avatar = new Avatar();

        try{
            avatar = avatarService.getAvaById(id);
        } catch(AvatarNotFoundException e){
            logger.error("Avatar with id " + id + " is not found.");
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType(avatar.getMediaType()));
        headers.setCacheControl(CacheControl.noCache().getHeaderValue());

        return new ResponseEntity<>(avatar.getData(), headers, HttpStatus.OK);
    }
}
