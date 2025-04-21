package ru.skypro.homework.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
public class Register {
    @Size(min = 4, max = 16, message = "От 2 до 16 символов")
    private String username;
    @Size(min = 8, max = 16, message = "От 8 до 16 символов")
    private String password;
    @Size(min = 2, max = 16, message = "От 2 до 16 символов")
    private String firstName;
    @Size(min = 2, max = 16, message = "От 2 до 16 символов")
    private String lastName;
    @Pattern(regexp = "\\+7\\s?\\(?\\d{3}\\)?\\s?\\d{3}-?\\d{2}-?\\d{2}",
            message = "Номер телефона должен соответствовать формату: +7 XXX XXX-XX-XX")
    private String phone;
    private Role role;
}
