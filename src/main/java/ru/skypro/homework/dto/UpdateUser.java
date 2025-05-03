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

    public UpdateUser(String firstName, String lastName, String phone) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
    }

    public UpdateUser() {
    }

    public @Size(min = 3, max = 16, message = "От 3 до 16 символов") String getFirstName() {
        return firstName;
    }

    public void setFirstName(@Size(min = 3, max = 16, message = "От 3 до 16 символов") String firstName) {
        this.firstName = firstName;
    }

    public @Size(min = 3, max = 16, message = "От 3 до 16 символов") String getLastName() {
        return lastName;
    }

    public void setLastName(@Size(min = 3, max = 16, message = "От 3 до 16 символов") String lastName) {
        this.lastName = lastName;
    }

    public @Pattern(regexp = "\\+7\\s?\\(?\\d{3}\\)?\\s?\\d{3}-?\\d{2}-?\\d{2}",
            message = "Номер телефона должен соответствовать формату: +7 XXX XXX-XX-XX") String getPhone() {
        return phone;
    }

    public void setPhone(@Pattern(regexp = "\\+7\\s?\\(?\\d{3}\\)?\\s?\\d{3}-?\\d{2}-?\\d{2}",
            message = "Номер телефона должен соответствовать формату: +7 XXX XXX-XX-XX") String phone) {
        this.phone = phone;
    }
}