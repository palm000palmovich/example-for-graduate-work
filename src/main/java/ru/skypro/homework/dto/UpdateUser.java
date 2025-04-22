package ru.skypro.homework.dto;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class UpdateUser {
    @Size(min = 3, max = 16, message = "От 3 до 16 символов")
    private String firstName;
    @Size(min = 3, max = 16, message = "От 3 до 16 символов")
    private String lastName;
    @Pattern(regexp = "\\+7\\s?\\(?\\d{3}\\)?\\s?\\d{3}-?\\d{2}-?\\d{2}",
            message = "Номер телефона должен соответствовать формату: +7 XXX XXX-XX-XX")
    private String phone;
}