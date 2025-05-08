package ru.skypro.homework.dto;

import javax.validation.constraints.Size;

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

    public String getCurrentPassword() {
        return currentPassword;
    }

    public void setCurrentPassword(String currentPassword) {
        this.currentPassword = currentPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    @Override
    public String toString() {
        return "NewPassword{" +
                "currentPassword='" + currentPassword + '\'' +
                ", newPassword='" + newPassword + '\'' +
                '}';
    }
}
