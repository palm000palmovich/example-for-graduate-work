package ru.skypro.homework.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import javax.validation.constraints.*;

public class CreateOrUpdateAd {
    @NotBlank(message = "Заголовок объявления")
    @Size(min = 4, max = 32, message = "От 4 до 32 символов")
    private String title;

    @NotNull(message = "Цена объявления")
    @Min(value = 0, message = "Цена не может быть отрицательной")
    @Max(value = 10000000, message = "Цена не может превышать 10 000 000")
    private Integer price;

    @NotBlank(message = "Описание объявления")
    @Size(min = 8, max = 64, message = "От 8 до 64 символов")
    private String description;

    public CreateOrUpdateAd(String title, Integer price, String description) {
        this.title = title;
        this.price = price;
        this.description = description;
    }

    public CreateOrUpdateAd() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}