package ru.skypro.homework.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.Size;

@Data
public class NewPassword {
    @Size(min = 8, max = 16, message = "От 8 до 16 символов")
    private String currentPassword;
    @Size(min = 8, max = 16, message = "От 8 до 16 символов")
    private String newPassword;

    public NewPassword() {}

    public NewPassword(String currentPassword, String newPassword) {
        this.currentPassword = currentPassword;
        this.newPassword = newPassword;
    }
}
