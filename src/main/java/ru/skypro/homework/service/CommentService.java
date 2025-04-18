package ru.skypro.homework.service;

import ru.skypro.homework.dto.Comment;

import java.util.List;

public interface CommentService {
    List<Comment> getComments(int adId);

    Comment addComment(int adId, Comment comment);

    void deleteComment(int adId, int commentId);

    Comment patchComment(int adId, int commentId);
}
