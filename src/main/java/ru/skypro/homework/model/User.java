package ru.skypro.homework.model;

import ru.skypro.homework.dto.Role;

import javax.persistence.*;
import java.util.UUID;
import javax.validation.constraints.Size;


@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private UUID id;

    @Column(name = "user_name", nullable = false)
    private String username;

    @Column(name = "password", nullable = false)
    @Size(min = 8, message = "Пароль должен содержать не менее 8 символов")
    private String password;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "phone", nullable = false)
    private String phone;

    @Column(name = "role")
    private Role role;
}
