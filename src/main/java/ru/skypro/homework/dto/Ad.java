package ru.skypro.homework.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Ad {

    private Integer author;

    private String image;

    private Long pk;

    private Integer price;

    private String title;

}
