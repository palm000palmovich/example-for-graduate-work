package ru.skypro.homework.dto;

import javax.validation.constraints.Size;

public class Login {
    @Size(min = 4, max = 16, message = "От 4 до 16 символов")
    private String username;
    @Size(min = 8, max = 16, message = "От 8 до 16 символов")
    private String password;

    public Login(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
