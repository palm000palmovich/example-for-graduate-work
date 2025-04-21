package ru.skypro.homework.dto;

import lombok.Data;

import java.util.List;

@Data
public class Ads {
    private Integer count;
    private List<Ad> results;

    public Ads(List<Ad> results) {
        this.count = results.size();
        this.results = results;
    }
}
