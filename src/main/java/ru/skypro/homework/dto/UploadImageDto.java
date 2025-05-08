package ru.skypro.homework.dto;

import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;

public class UploadImageDto {
    @NotNull(message = "Файл обязателен")
    private MultipartFile image;

    public MultipartFile getImage() {
        return image;
    }

    public void setImage(MultipartFile image) {
        this.image = image;
    }
}
