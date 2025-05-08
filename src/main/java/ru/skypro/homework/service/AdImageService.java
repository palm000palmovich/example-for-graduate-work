package ru.skypro.homework.service;

import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.model.AdImage;

import java.io.IOException;

public interface AdImageService {
    void addAdImage(Integer adId, MultipartFile multipartFile) throws IOException;
    AdImage getAdImage(Integer adId);
}
