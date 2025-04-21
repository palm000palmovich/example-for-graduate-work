package ru.skypro.homework.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
public class Login {
    private String username;
    private String password;
}
