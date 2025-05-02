package ru.skypro.homework.dto;

import java.util.List;

public class CommentsDTO {
    private int count;
    private List<CommentDTO> results;

    public CommentsDTO(int count, List<CommentDTO> results) {
        this.count = count;
        this.results = results;
    }

    public CommentsDTO() {

    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<CommentDTO> getResults() {
        return results;
    }

    public void setResults(List<CommentDTO> results) {
        this.results = results;
    }
}
