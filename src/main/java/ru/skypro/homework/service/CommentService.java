package ru.skypro.homework.service;

import ru.skypro.homework.dto.CommentsDTO;
import ru.skypro.homework.dto.CreateOrUpdateComment;

public interface CommentService {
    CommentsDTO getComments(Long id);

    CreateOrUpdateComment addComment(Long id, CreateOrUpdateComment createOrUpdateComment);

    void deleteComment(Long adId, Long commentId);

    CreateOrUpdateComment updateComment(Long adId, Long commentId, CreateOrUpdateComment createOrUpdateComment);
}
