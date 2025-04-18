package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.Comment;
import ru.skypro.homework.repository.CommentRepository;
import ru.skypro.homework.service.CommentService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;

    /**
     * Получение комментариев объявления
     *
     * @param adId - id объявления
     */
    @Override
    public List<Comment> getComments(int adId) {
        return commentRepository.findByAdId(adId);
    }

    /**
     * Добавление комментария к объявлению
     *
     * @param adId    - id объявления
     * @param comment - комментарий
     */
    @Override
    public Comment addComment(int adId, Comment comment) {
        comment.setAdId(adId);
        return commentRepository.save(comment);
    }

    /**
     * Удаление комментария
     *
     * @param adId      - id объявления
     * @param commentId - id комментария
     */
    @Override
    public void deleteComment(int adId, int commentId) {
        Comment comment = commentRepository.findByIdAndAdId(commentId, adId);
        commentRepository.delete(comment);
    }

    /**
     * Обновление комментария
     *
     * @param adId      - id объявления
     * @param commentId - id комментария
     */
    @Override
    public Comment patchComment(int adId, int commentId) {
        Comment comment = commentRepository.findByIdAndAdId(commentId, adId);
        commentRepository.save(comment);
        return comment;
    }
}
