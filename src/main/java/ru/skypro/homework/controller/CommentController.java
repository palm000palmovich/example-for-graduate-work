package ru.skypro.homework.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.dto.Comment;
import ru.skypro.homework.service.impl.CommentServiceImpl;

import java.util.List;

@RestController
@RequestMapping("/ads")
@RequiredArgsConstructor
public class CommentController {
    private final CommentServiceImpl commentServiceImpl;

    @GetMapping("/{adId}/comments")
    public List<Comment> getComments(@PathVariable int adId) {
        return commentServiceImpl.getComments(adId);
    }

    @PostMapping("/{adId}/comments")
    public Comment addComment(@PathVariable int adId, @RequestBody Comment comment) {
        return commentServiceImpl.addComment(adId, comment);
    }

    @DeleteMapping("/{adId}/comments/{commentId}")
    public void deleteComment(@PathVariable int adId, @PathVariable int commentId) {
        commentServiceImpl.deleteComment(adId, commentId);
    }

    @PatchMapping("/{adId}/comments/{commentId}")
    public Comment patchComment(@PathVariable int adId, @PathVariable int commentId) {
        return commentServiceImpl.patchComment(adId, commentId);
    }
}
