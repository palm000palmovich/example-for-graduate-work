package ru.skypro.homework.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentDTO {
    private Long author;
    private String authorImage;
    private String authorFirstName;
    private Long createdAt;
    private Long pk;
    private String text;
}
