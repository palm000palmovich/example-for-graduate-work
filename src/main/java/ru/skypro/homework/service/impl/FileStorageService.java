package ru.skypro.homework.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class FileStorageService {
    private final Path storageDirectory = Paths.get("uploads");

    public FileStorageService() {
        try {
            if (!Files.exists(storageDirectory)) {
                Files.createDirectories(storageDirectory);
            }
        } catch (IOException e) {
            throw new RuntimeException("Не удалось создать директорию");
        }
    }

    public String saveAdImage(MultipartFile file) {
        if (file.isEmpty()) {
            throw new IllegalArgumentException("Файл пустой");
        }
        String fileExtension = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
        String uniqueFileName = UUID.randomUUID() + fileExtension;

        Path filePath = storageDirectory.resolve(uniqueFileName);

        try (InputStream inputStream = file.getInputStream();
             OutputStream outputStream = new FileOutputStream(filePath.toFile())) {
            inputStream.transferTo(outputStream);
        } catch (IOException e) {
            throw new RuntimeException("Не удалось сохранить файл");
        }
        return uniqueFileName;
    }
}
