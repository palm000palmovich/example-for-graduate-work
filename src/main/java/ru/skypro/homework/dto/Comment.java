package ru.skypro.homework.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Comment {
    private int author;
    private String authorImage;
    private String authorFirstName;
    private Long createdAt;
    private int pk;
    private String text;
}
