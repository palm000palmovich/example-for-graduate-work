package ru.skypro.homework.service;

import ru.skypro.homework.dto.CommentsDTO;
import ru.skypro.homework.dto.CreateOrUpdateComment;

public interface CommentService {
    CommentsDTO getComments(Integer id);

        CreateOrUpdateComment addComment(Integer id, CreateOrUpdateComment createOrUpdateComment);

    void deleteComment(Integer adId, Integer commentId);

    CreateOrUpdateComment updateComment(Integer adId, Integer commentId, CreateOrUpdateComment createOrUpdateComment);
}
