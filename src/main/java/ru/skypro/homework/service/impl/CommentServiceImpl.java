package ru.skypro.homework.service.impl;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.Ad;
import ru.skypro.homework.dto.CommentsDTO;
import ru.skypro.homework.dto.CreateOrUpdateComment;
import ru.skypro.homework.dto.User;
import ru.skypro.homework.mapper.CommentMapper;
import ru.skypro.homework.model.Comment;
import ru.skypro.homework.repository.AdRepository;
import ru.skypro.homework.repository.CommentRepository;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.CommentService;

import javax.persistence.EntityNotFoundException;
import java.util.Collections;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private AdRepository adRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CommentMapper commentMapper;

    /** Получаем объявление по ID
     * Получаем все комментарии для объявления
     * Преобразуем в DTO
     * @param adId
     */
    @Override
    public CommentsDTO getComments(Long adId) {
        Ad ad = adRepository.findById(adId)
                .orElseThrow(() -> new EntityNotFoundException("Объявление с ID " + adId + " не найдено"));

        List<Comment> comments = commentRepository.findByAd(ad);

        return commentMapper.toCommentsDTO(comments);
    }


    /** Получаем объявление по ID
     * Создаем новый комментарий
     * Преобразуем в DTO
     * Вручную устанавливаем комментарию объявление, автора.
     * Предполагается, что текущий пользователь доступен через SecurityContext.
     * Сохраняем комментарий. Возвращаем DTO нового комментария
     * @param adId
     */
    @Override
    public CreateOrUpdateComment addComment(Long adId, CreateOrUpdateComment createOrUpdateComment) {
        Ad ad = adRepository.findById(adId)
                .orElseThrow(() -> new EntityNotFoundException("Объявление с ID " + adId + " не найдено"));

        Comment comment = commentMapper.toComment(createOrUpdateComment);
        comment.setAd(ad);
        comment.setAuthor(getCurrentUser());

        Comment savedComment = commentRepository.save(comment);

        return commentMapper.toCreateOrUpdateComment(savedComment);
    }


    /** Проверяем, существует ли объявление
     * Удаляем комментарий
     * @param adId
     * @param commentId
     */
    @Override
    public void deleteComment(Long adId, Long commentId) {
        adRepository.findById(adId)
                .orElseThrow(() -> new EntityNotFoundException("Объявление с ID " + adId + " не найдено"));

        commentRepository.deleteById(commentId);
    }


    /** Проверяем, существует ли объявление
     * Получаем комментарий по ID
     * Обновляем текст комментария
     * Сохраняем обновленный комментарий
     * Возвращаем DTO обновленного комментария
     * @param adId
     * @param commentId
     * @param createOrUpdateComment
     * @return
     */
    @Override
    public CreateOrUpdateComment updateComment(Long adId, Long commentId, CreateOrUpdateComment createOrUpdateComment) {
        adRepository.findById(adId)
                .orElseThrow(() -> new EntityNotFoundException("Объявление с ID " + adId + " не найдено"));

        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new EntityNotFoundException("Комментарий с ID " + commentId + " не найден"));

        comment.setText(createOrUpdateComment.getText());

        Comment updatedComment = commentRepository.save(comment);

        return commentMapper.toCreateOrUpdateComment(updatedComment);
    }

    /**
     * Получаем текущего пользователя из SecurityContextHolder
     */
        private User getCurrentUser() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new EntityNotFoundException("Пользователь с логином " + username + " не найден"));
    }
}

