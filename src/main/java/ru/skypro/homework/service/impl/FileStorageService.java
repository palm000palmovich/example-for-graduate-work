package ru.skypro.homework.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.webjars.NotFoundException;
import ru.skypro.homework.dto.AdsDto;
import ru.skypro.homework.mapper.AdMapper;
import ru.skypro.homework.model.Ad;
import ru.skypro.homework.model.AdImage;
import ru.skypro.homework.repository.AdRepository;
import ru.skypro.homework.repository.ImageRepository;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class FileStorageService {

    private final AdMapper adMapper;
    @Value("${example-for-graduate-work-main.avatar.dir.path}")
    private String avatarsDir;

    private final AdRepository adRepository;
    private final ImageRepository imageRepository;

    public FileStorageService(AdRepository adRepository, ImageRepository imageRepository, AdMapper adMapper) {
        this.adRepository = adRepository;
        this.imageRepository = imageRepository;
        this.adMapper = adMapper;
    }
    public void uploadImage(Integer adId, MultipartFile file) throws IOException {
        Ad ad = adRepository.findById(adId)
                .orElseThrow(() -> new RuntimeException("Ad not found with id: " + adId));

        imageRepository.findByAdId(adId).ifPresent(imageRepository::delete);
        Path imagePath = Path.of(avatarsDir, adId + "_" + file.getOriginalFilename());
        Files.createDirectories(imagePath.getParent());
        Files.deleteIfExists(imagePath);

        try (InputStream inputStream = file.getInputStream();
             OutputStream outputStream = Files.newOutputStream(imagePath);
             BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
             BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(outputStream)) {
            bufferedInputStream.transferTo(bufferedOutputStream);
        }

        AdImage adImage = new AdImage();
        adImage.setFilePath(imagePath.toString());
        adImage.setFileSize(file.getSize());
        adImage.setMediatype(file.getContentType());
        adImage.setData(file.getBytes());
        adImage.setAd(ad);

        ad.setImage("/images/" + imagePath.toString());

        adRepository.save(ad);
        imageRepository.save(adImage);
    }

    public AdImage getImageById(Integer id) {
        return imageRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Image not found with id: " + id));
    }

    public byte[] getImageData(Integer id) {
        AdImage adImage = getImageById(id);
        return adImage.getData();
    }

}
