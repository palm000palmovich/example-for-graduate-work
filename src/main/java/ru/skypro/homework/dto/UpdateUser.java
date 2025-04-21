package ru.skypro.homework.dto;

import javax.validation.constraints.Pattern;

public class UpdateUser {
    private String firstName;
    private String lastName;
    @Pattern(regexp = "\\+7\\s?\\(?\\d{3}\\)?\\s?\\d{3}-?\\d{2}-?\\d{2}",
            message = "Номер телефона должен соответствовать формату: +7 XXX XXX-XX-XX")
    private String phone;
}