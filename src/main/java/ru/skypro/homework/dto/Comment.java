package ru.skypro.homework.dto;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "comments")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private int adId;

    private int author;
    private String authorImage;
    private String authorFirstName;
    private LocalDateTime createdAt;
    private int pk;

    @Column(nullable = false)
    private String text;

    @Transient
    private int count;

    @Override
    public String toString() {
        return "count\": " + count +",\n"
                + "results\": " + "[\n" + "{\n"
                + "author\": " + author +",\n"
                + "authorImage\": " + authorImage +",\n"
                + "authorFirstName\": " + authorFirstName +",\n"
                + "createdAt\": " + createdAt +",\n"
                + "pk\": " + pk + "}\n" + "]\n";
    }
}
