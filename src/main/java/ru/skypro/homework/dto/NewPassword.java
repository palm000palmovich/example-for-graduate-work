package ru.skypro.homework.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
public class NewPassword {
    private String currentPassword;
    private String newPassword;

    public NewPassword() {}

    public NewPassword(String currentPassword, String newPassword) {
        this.currentPassword = currentPassword;
        this.newPassword = newPassword;
    }
}
