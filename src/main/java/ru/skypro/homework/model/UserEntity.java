package ru.skypro.homework.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.skypro.homework.dto.Ad;
import ru.skypro.homework.dto.Comment;
import ru.skypro.homework.dto.Role;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
@Entity
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long pk;

    @Column(nullable = false)
    @Size(min = 4, max = 16, message = "От 4 до 16 символов")
    private String username;

    @Column(nullable = false)
    @Size(min = 8, max = 16, message = "От 8 до 16 символов")
    private String password;

    @Column(nullable = false)
    @Size(min = 3, max = 16, message = "От 3 до 16 символов")
    private String firstName;

    @Column(nullable = false)
    @Size(min = 3, max = 16, message = "От 3 до 16 символов")
    private String lastName;

    @Column(nullable = false)
    @Pattern(regexp = "\\+7\\s?\\(?\\d{3}\\)?\\s?\\d{3}-?\\d{2}-?\\d{2}",
            message = "Номер телефона должен соответствовать формату: +7 XXX XXX-XX-XX")
    private String phone;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @Column(nullable = false)
    private String image;

//    @OneToMany(mappedBy = "author_id")
//    private List<Ad> ads;
//
//    @OneToMany(mappedBy = "author_id")
//    private List<Comment> comments;

}