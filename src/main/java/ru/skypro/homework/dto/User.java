package ru.skypro.homework.dto;

import lombok.Data;
import ru.skypro.homework.dto.Role;

import javax.persistence.*;
import java.util.UUID;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;


@Data
public class User {
    private Long id;
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    @Pattern(regexp = "\\+7\\s?\\(?\\d{3}\\)?\\s?\\d{3}-?\\d{2}-?\\d{2}",
            message = "Номер телефона должен соответствовать формату: +7 XXX XXX-XX-XX")
    private String phone;
    private Role role;
    private String image;
}
