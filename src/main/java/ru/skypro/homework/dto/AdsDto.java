package ru.skypro.homework.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdsDto {
    private Integer count;
    private List<AdDto> results;

    public AdsDto(List<AdDto> results) {
        this.count = results.size();
        this.results = results;
    }
}