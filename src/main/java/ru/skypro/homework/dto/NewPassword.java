package ru.skypro.homework.dto;

import lombok.Data;

import javax.validation.constraints.Size;

@Data
public class NewPassword {

    @Size(min = 8, max = 16)
    private String currentPassword;

    @Size(min = 8, max = 16)
    private String newPassword;

    public NewPassword(String currentPassword, String newPassword) {
        this.currentPassword = currentPassword;
        this.newPassword = newPassword;
    }
}
