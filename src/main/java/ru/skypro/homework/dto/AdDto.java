package ru.skypro.homework.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AdDto {
    private Integer author;
    private String image;
    private Long pk;
    private Integer price;
    private String title;
}
