package ru.skypro.homework.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.skypro.homework.dto.AdDto;
import ru.skypro.homework.dto.User;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.Instant;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "comments")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Текст комментария не может быть пустым")
    @Size(min = 1, max = 500, message = "Текст комментария должен содержать от 1 до 500 символов")
    @Column(nullable = false, length = 500)
    private String text;

    @ManyToOne
    @JoinColumn(name = "ad_id")
    @JsonBackReference
    private AdDto ad;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id", nullable = false)
    @JsonBackReference
    private User author;

    @Column(nullable = false)
    private Instant createdAt;

    @PrePersist
    protected void onCreate() {
        createdAt = Instant.now();
    }
}




