package ru.skypro.homework.dto;

import java.util.List;

public class AdsDto {
    private Integer count;
    private List<AdDto> results;

    public AdsDto(List<AdDto> results) {
        this.count = results.size();
        this.results = results;
    }

    public AdsDto() {
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public List<AdDto> getResults() {
        return results;
    }

    public void setResults(List<AdDto> results) {
        this.results = results;
    }
}