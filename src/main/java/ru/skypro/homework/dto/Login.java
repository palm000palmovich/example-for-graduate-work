package ru.skypro.homework.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
public class Login {
    @Size(min = 4, max = 16, message = "От 4 до 16 символов")
    private String username;
    @Size(min = 8, max = 16, message = "От 8 до 16 символов")
    private String password;
}
