package ru.skypro.homework.dto;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class CreateOrUpdateAd {
    @NotBlank(message = "Заголовок объявления")
    @Size(min = 4, max = 32, message = "От 4 до 32 символов")
    private String title;

    @NotBlank(message = "Цена объявления")
    @Min(value = 0, message = "Цена не может быть отрицательной")
    @Max(value = 10000000, message = "Цена не может превышать 10 000 000")
    private Integer price;

    @NotBlank(message = "Описание объявления")
    @Size(min = 8, max = 64, message = "От 8 до 64 символов")
    private String description;

    public void checkLombock(){
        
    }
}
