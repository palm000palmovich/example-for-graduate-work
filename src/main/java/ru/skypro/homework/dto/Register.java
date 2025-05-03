package ru.skypro.homework.dto;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class Register {
    @Size(min = 4, max = 16, message = "От 4 до 16 символов")
    private String username;
    @Size(min = 8, max = 16, message = "От 8 до 16 символов")
    private String password;
    @Size(min = 3, max = 16, message = "От 3 до 16 символов")
    private String firstName;
    @Size(min = 3, max = 16, message = "От 3 до 16 символов")
    private String lastName;
    @Pattern(regexp = "\\+7\\s?\\(?\\d{3}\\)?\\s?\\d{3}-?\\d{2}-?\\d{2}",
            message = "Номер телефона должен соответствовать формату: +7 XXX XXX-XX-XX")
    private String phone;
    private Role role;

    public Register(String username, String password, String firstName, String lastName, String phone, Role role) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.role = role;
    }

    public Register() {
    }

    public @Size(min = 4, max = 16, message = "От 4 до 16 символов") String getUsername() {
        return username;
    }

    public void setUsername(@Size(min = 4, max = 16, message = "От 4 до 16 символов") String username) {
        this.username = username;
    }

    public @Size(min = 8, max = 16, message = "От 8 до 16 символов") String getPassword() {
        return password;
    }

    public void setPassword(@Size(min = 8, max = 16, message = "От 8 до 16 символов") String password) {
        this.password = password;
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

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
