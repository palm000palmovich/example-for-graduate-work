package ru.skypro.homework.dto;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class UserDto {
    private Integer id;
    @Size(min = 4, max = 16, message = "От 4 до 16 символов")
    private String email;
    @Size(min = 3, max = 16, message = "От 3 до 16 символов")
    private String firstName;
    @Size(min = 3, max = 16, message = "От 3 до 16 символов")
    private String lastName;
    @Pattern(regexp = "\\+7\\s?\\(?\\d{3}\\)?\\s?\\d{3}-?\\d{2}-?\\d{2}",
            message = "Номер телефона должен соответствовать формату: +7 XXX XXX-XX-XX")
    private String phone;
    private Role role;
    private String image;

    public UserDto(Integer id, String email, String firstName, String lastName, String phone, Role role, String image) {
        this.id = id;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.role = role;
        this.image = image;
    }

    public UserDto() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public @Size(min = 4, max = 16, message = "От 4 до 16 символов") String getEmail() {
        return email;
    }

    public void setEmail(@Size(min = 4, max = 16, message = "От 4 до 16 символов") String email) {
        this.email = email;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
